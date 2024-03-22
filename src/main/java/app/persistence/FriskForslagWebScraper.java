package app.persistence;

import app.entities.FriskForslagRecipe;
import app.exceptions.DatabaseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * homemade webscrape methods tailored for Opskrifter.dk (may or may not work)
 */
public class FriskForslagWebScraper
{
    /**
     * Crawl and scrape recipes from Opskrifter.dk
     * @param cp database connection
     * @param nRecipes number of recipes you wish to import
     */
    public static void ScrapeRecipesFromOpskrifterDK(ConnectionPool cp, int nRecipes)
    {
        List<FriskForslagRecipe> recipesView = null;
        try {
            recipesView = FriskForslagMapper.FilterRecipesIngredients(cp, "%");
        } catch (DatabaseException e) {
            System.err.println(e.getMessage());
        }

        int i = 0, j = 0;
        while (true) {
            ++i;
            String url = "https://www.opskrifter.dk/sog?query=%25&page=" + i;
            String html = fetchHTML(url);
            if (html.isEmpty())
                break;
            List<String> recipeURLs = scrapeRecipeURLs(html);
            for (String recipeURL : recipeURLs) {
                if (j >= nRecipes)
                    return;
                boolean skip = false;
                for (FriskForslagRecipe rec : recipesView) {
                    if (rec.Src().equals(recipeURL)) {
                        skip = true;
                        break;
                    }
                }
                if (skip)
                    continue;
                try {
                    FriskForslagMapper.InsertRecipe(cp, ScrapeRecipeSpec(recipeURL));
                    ++j;
                } catch (DatabaseException e) {
                    System.err.print("");
                }
            }
        }
    }

    private static String fetchHTML(String address)
    {
        StringBuilder responseHTML = new StringBuilder();
        try {
            URI uri = new URI(address);
            URL url = uri.toURL();
            // System.out.println("fecthHTML::\t URL:" + url);
            HttpsURLConnection c = (HttpsURLConnection) url.openConnection();

            c.setRequestMethod("GET");
            c.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = c.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader response = new BufferedReader(
                        new InputStreamReader(c.getInputStream()));
                String line;
                while ((line = response.readLine()) != null)
                    responseHTML.append(line);
                response.close();
            }

        } catch (URISyntaxException | IOException e) {
            System.err.println(e.getMessage());
        }
        return responseHTML.toString();
    }

    private static List<String> scrapeRecipeURLs(String html)
    {
        List<String> recipeURLs = new ArrayList<>();

        int htmlIndex_0 = 0, htmlIndex_1;
        while (true) {
            html = html.substring(htmlIndex_0);
            htmlIndex_0 = html.indexOf("href=");
            if (htmlIndex_0 < 0)
                break;
            while (htmlIndex_0 < html.length() &&
                    html.charAt(htmlIndex_0) != '"')
                ++htmlIndex_0;
            htmlIndex_1 = htmlIndex_0 + 1;
            while (htmlIndex_1 < html.length() &&
                    html.charAt(htmlIndex_1) != '"')
                ++htmlIndex_1;
            String candidateURL = html.substring(htmlIndex_0 + 1, htmlIndex_1);
            if (candidateURL.contains("/opskrift/"))
                recipeURLs.add(candidateURL);
            htmlIndex_0 = htmlIndex_1 + 1;
        }

        return recipeURLs;
    }

    private static FriskForslagRecipe ScrapeRecipeSpec(String url)
    {
        /*
         * Fetch html
         */
        String htmlDoc = fetchHTML(url);
        /*
         * Get full spec
         */
        htmlDoc = htmlDoc.substring(
                htmlDoc.indexOf("<script type=\"application/ld+json\">"));
        htmlDoc = htmlDoc.substring(0, htmlDoc.indexOf("</script>"));
        /*
         * parse HTML
         */
        String name = "", descr = "", proc = "", src = "", author = "", img = "";
        List<String> ingr = new ArrayList<>(),
                quant = new ArrayList<>(),
                units = new ArrayList<>();

        int quoteIndex_0 = 0, quoteIndex_1 = 0;
        String mode = "";
        while (true) {
            // go to next quote-pair & parse its content
            while (quoteIndex_0 < htmlDoc.length() &&
                    htmlDoc.charAt(quoteIndex_0) != '"')
                ++quoteIndex_0;
            quoteIndex_1 = quoteIndex_0 + 1;
            while (quoteIndex_1 < htmlDoc.length() &&
                    htmlDoc.charAt(quoteIndex_1) != '"')
                ++quoteIndex_1;
            if (quoteIndex_0 >= htmlDoc.length() ||
                    quoteIndex_1 >= htmlDoc.length())
                break;

            String quoteContent = htmlDoc.substring(quoteIndex_0 + 1, quoteIndex_1);
            //System.out.println(quoteContent);
            quoteIndex_0 = quoteIndex_1 + 1;


            // switch modes
            switch (quoteContent) {
                case "Recipe" -> mode = "Recipe";
                case "image" -> mode = "image";
                case "author" -> mode = "author";
                case "description" -> mode = "description";
                case "recipeIngredient" -> mode = "recipeIngredient";
                case "recipeInstructions" -> mode = "recipeInstructions";
                case "url" -> mode = "url";
                default -> {
                    if (mode.equals("Recipe")) {
                        switch (quoteContent) {
                            case "name" -> {
                            }
                            default -> {
                                name = quoteContent;
                                mode = "";
                            }
                        }
                    }
                    if (mode.equals("image")) {
                        switch (quoteContent) {
                            case "name" -> {
                            }
                            default -> {
                                img = quoteContent;
                                mode = "";
                            }
                        }
                    }
                    if (mode.equals("author")) {
                        switch (quoteContent) {
                            case "@type", "Person", "name" -> {
                            }
                            default -> {
                                author = quoteContent;
                                mode = "";
                            }
                        }
                    }
                    if (mode.equals("description")) {
                        switch (quoteContent) {
                            case "name" -> {
                            }
                            default -> {
                                descr = quoteContent;
                                mode = "";
                            }
                        }
                    }
                    if (mode.equals("recipeIngredient")) {
                        String[] ingrSplit = quoteContent.split(" ");
                        if (ingrSplit.length > 2) {
                            quant.add(ingrSplit[0]);
                            units.add(ingrSplit[1]);
                            String ingrToAdd = "";
                            for (int i = 2; i < ingrSplit.length; ++i) {
                                ingrToAdd = ingrToAdd + ingrSplit[i];
                                if (i < ingrSplit.length - 1)
                                    ingrToAdd = ingrToAdd + " ";
                            }
                            ingr.add(ingrToAdd);
                        } else {
                            quant.add("");
                            units.add("");
                            ingr.add(quoteContent);
                        }
                    }
                    if (mode.equals("recipeInstructions")) {
                        switch (quoteContent) {
                            case "name", "@type", "HowToStep", "Procedure", "text" -> {
                            }
                            default -> {
                                proc = quoteContent;
                                mode = "";
                            }
                        }
                    }
                    if (mode.equals("url")) {
                        switch (quoteContent) {
                            case "name" -> {
                            }
                            default -> {
                                src = quoteContent;
                                mode = "";
                            }
                        }
                    }
                }
            }
        }

        Long[] quantAsInt = new Long[quant.size()];
        for (int i = 0; i < quantAsInt.length; ++i) {
            try {
                quantAsInt[i] = Long.parseLong(quant.get(i));
            } catch (NumberFormatException e) {
                quantAsInt[i] = (long) 0;
            }
        }

        return new FriskForslagRecipe(0,
                name, descr, proc, ingr.toArray(new String[0]),
                quantAsInt, units.toArray(new String[0]),
                src, author, img);
    }


}
