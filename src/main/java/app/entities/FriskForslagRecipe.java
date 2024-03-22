package app.entities;

public record FriskForslagRecipe(int Id,
                                 String Name,
                                 String Descr,
                                 String Proc,
                                 String[] Ingredients,
                                 Long[] Quantities,
                                 String[] Units,
                                 String Src,
                                 String Author,
                                 String Img) {
}
