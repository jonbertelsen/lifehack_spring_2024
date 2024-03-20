package app.entities;

import java.math.BigInteger;

public record FriskForslagRecipe(int Id,
                                 String Name,
                                 String Descr,
                                 String Proc,
                                 String[] Ingredients,
                                 Long[] Quantities,
                                 String[] Units,
                                 String Src) {
}
