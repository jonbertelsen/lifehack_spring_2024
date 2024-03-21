package app.persistence;

import app.entities.FriskForslagRecipe;
import app.exceptions.DatabaseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

public class FriskForslagMapper {
    public static List<FriskForslagRecipe> FilterRecipesIngredients(ConnectionPool cp, String... foodItems)
            throws DatabaseException {
        List<FriskForslagRecipe> filteredRecipes = new ArrayList<>();

        StringBuilder sqlCondition = new StringBuilder();
        for (int i = 0; i < foodItems.length; ++i) {
            // TODO: guard against injections
            if (!sqlArgEnsureSafe(foodItems[i]))
                continue;
            sqlCondition.append("'" + foodItems[i] + "' ILIKE ANY(ingredients) ");
            if (i < foodItems.length - 1)
                sqlCondition.append(" OR ");
        }

        String sql = """
                SELECT * FROM friskforslag_opskrift\s
                WHERE\s
                """
                + sqlCondition;

        try (
                Connection c = cp.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                filteredRecipes.add(new FriskForslagRecipe(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("procedure"),
                        (String[]) rs.getArray("ingredients").getArray(),
                        (Long[]) rs.getArray("quantities").getArray(),
                        (String[]) rs.getArray("units").getArray(),
                        rs.getString("source"),
                        rs.getString("author"),
                        rs.getString("img")));
            }
        } catch (SQLException e) {
            throw new DatabaseException("fejl i søgning af opskrifter");
        }
        return filteredRecipes;
    }

    public static FriskForslagRecipe SelectRecipeByName(ConnectionPool cp, String name) throws DatabaseException {
        // TODO: guard against injections
        if (!sqlArgEnsureSafe(name))
            return null;
        String sql = "SELECT * FROM friskforslag_opskrift WHERE name ILIKE '" + name + "'";
        try (
                Connection c = cp.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new FriskForslagRecipe(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("procedure"),
                        (String[]) rs.getArray("ingredients").getArray(),
                        (Long[]) rs.getArray("quantities").getArray(),
                        (String[]) rs.getArray("units").getArray(),
                        rs.getString("source"),
                        rs.getString("author"),
                        rs.getString("img"));
            else
                return null;
        } catch (SQLException e) {
            throw new DatabaseException("fejl i søgning af opskrifter");
        }
    }

    public static int InsertRecipe(ConnectionPool cp, FriskForslagRecipe rec) throws DatabaseException {
        String sql = """
                INSERT INTO public.friskforslag_opskrift(
                id, name, description, procedure, ingredients, quantities, units, source, author, img)
                VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
        try (
             Connection c = cp.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, rec.Name());
            ps.setString(2, rec.Descr());
            ps.setString(3, rec.Proc());
            ps.setArray(4, c.createArrayOf("varchar", rec.Ingredients()));
            ps.setArray(5, c.createArrayOf("bigint", rec.Quantities()));
            ps.setArray(6, c.createArrayOf("varchar", rec.Units()));
            ps.setString(7, rec.Src());
            ps.setString(8, rec.Author());
            ps.setString(9, rec.Img());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Fejl ved indsættelse af opskriften '" + rec.Name() + "' i database, findes opskriften allerede?");
            return -1;
        }
    }

    public static FriskForslagRecipe ScrapeHTML_OpskrifterDK(ConnectionPool cp, String url) {
        /*
         * Fetch html
         */
        String htmlDoc = fetchHTML(url);

        // temp save html to local txt file
        // Path htmlTextOutputFile = Paths.get("htmlTextOutputFile.html");
        // try (OutputStream out = new BufferedOutputStream(
        // Files.newOutputStream(htmlTextOutputFile, CREATE, WRITE))) {
        // out.write(htmlDoc.getBytes());
        // } catch (IOException x) {
        // System.err.println(x);
        // }

        /*
         * Get full spec
         */

        htmlDoc = htmlDoc.substring(
                htmlDoc.indexOf("<script type=\"application/ld+json\">"));
        htmlDoc = htmlDoc.substring(0, htmlDoc.indexOf("</script>"));
        // not necessary ?
        String[] htmlDocSplit = htmlDoc.split(",");
        for (String s : htmlDocSplit)
            s = s.trim();
        // ------

        // parse html
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
            if (quoteContent.equals("Recipe"))
                mode = "Recipe";
            else if (quoteContent.equals("image"))
                mode = "image";
            else if (quoteContent.equals("author"))
                mode = "author";
            else if (quoteContent.equals("description"))
                mode = "description";
            else if (quoteContent.equals("recipeIngredient"))
                mode = "recipeIngredient";
            else if (quoteContent.equals("recipeInstructions"))
                mode = "recipeInstructions";
            else if (quoteContent.equals("url"))
                mode = "url";
            else {
                if (mode.equals("Recipe")) {
                    if (quoteContent.equals("name")) {
                    } else {
                        name = quoteContent;
                        mode = "";
                    }
                }
                if (mode.equals("image")) {
                    if (quoteContent.equals("name")) {
                    } else {
                        img = quoteContent;
                        mode = "";
                    }
                }
                if (mode.equals("author")) {
                    if (quoteContent.equals("@type")) {
                    }
                    if (quoteContent.equals("Person")) {
                    }
                    if (quoteContent.equals("name")) {
                    } else {
                        author = quoteContent;
                        mode = "";
                    }
                }
                if (mode.equals("description")) {
                    if (quoteContent.equals("name")) {
                    } else {
                        descr = quoteContent;
                        mode = "";
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
                    if (quoteContent.equals("name")) {
                    } else if (quoteContent.equals("@type")) {
                    } else if (quoteContent.equals("HowToStep")) {
                    } else if (quoteContent.equals("Procedure")) {
                    } else if (quoteContent.equals("text")) {
                    } else {
                        proc = quoteContent;
                        mode = "";
                    }
                }
                if (mode.equals("url")) {
                    if (quoteContent.equals("name")) {
                    } else {
                        src = quoteContent;
                        mode = "";
                    }
                }
            }
        }

        Long quantAsInt[] = new Long[quant.size()];
        for (int i = 0; i < quantAsInt.length; ++i){
            try{
                quantAsInt[i] = Long.parseLong(quant.get(i));
            }
            catch (NumberFormatException e){
                quantAsInt[i] = (long) 0;
            }
        }
        FriskForslagRecipe finalRecipe = new FriskForslagRecipe(0,
                                                                name,
                                                                descr,
                                                                proc,
                                                                ingr.toArray(new String[0]),
                                                                quantAsInt,
                                                                units.toArray(new String[0]),
                                                                src,
                                                                author,
                                                                img);

        return finalRecipe;
    }

    private static String fetchHTML(String address) {
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
            e.printStackTrace();
        }
        return responseHTML.toString();
    }

    private static boolean sqlArgEnsureSafe(String arg) {
        Pattern p = Pattern.compile("[;:,\t\r\n]");
        Matcher m = p.matcher(arg);
        return !m.matches();
    }
}
