package app.persistence;

import app.entities.CountryCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimezoneMapper
{
    public static int calculateTimeDifference(String fromCountryCode, String toCountryCode, ConnectionPool pool)
    {
        int fromOffset = getTimezoneOffset(fromCountryCode, pool);
        int toOffset = getTimezoneOffset(toCountryCode, pool);
        return toOffset - fromOffset;
    }

    public static int getTimezoneOffset(String countryCode, ConnectionPool pool)
    {
        String sql = "SELECT gmt_offset, dst " +
                "FROM time_zone " +
                "WHERE country_code = ? " +
                "ORDER BY time_start DESC LIMIT 1";

        try (
                Connection conn = pool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {

            pstmt.setString(1, countryCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                int gmtOffset = rs.getInt("gmt_offset");
                String dst = rs.getString("dst");
                int dstOffset = calculateDstOffset(dst);
                return gmtOffset + dstOffset; // den samlede offset inklusiv DST
            } else
            {
                throw new SQLException("No timezone information found for country code: " + countryCode);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Database error while retrieving timezone information", e);
        }
    }

    public static List<CountryCode> getCountryCodes(ConnectionPool pool)
    {
        List<CountryCode> countryCodeList = new ArrayList<>();
        String sql = "SELECT country_code, country_name FROM country";

        try (
                Connection conn = pool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");
                countryCodeList.add(new CountryCode(countryCode, countryName));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Database error while retrieving timezone information", e);
        }
        return countryCodeList;
    }

    private static int calculateDstOffset(String dst)
    {
        if ("Yes".equalsIgnoreCase(dst))
        {
            return 3600; //Tilføjer 1 time
        }
        return 0;
    }


}
