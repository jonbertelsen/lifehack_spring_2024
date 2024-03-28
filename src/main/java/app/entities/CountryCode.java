package app.entities;

public class CountryCode
{
    private String countryCode;
    private String countryName;

    public CountryCode(String countryCode, String countryName)
    {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public String getCountryName()
    {
        return countryName;
    }

}
