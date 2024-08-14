package com.farmacy.Modules.country.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.country.domain.entity.Country;
import com.farmacy.Modules.country.domain.service.CountryService;

public class CountryRepository implements CountryService {
    private Connection connection;
    
    public CountryRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCountry(Country country) {
        try {
            String query = "INSERT INTO country (codecountry, namecountry) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getCodeCountry());
            ps.setString(2, country.getNameCountry());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCountry(String codeCountry) {
        String query = "DELETE FROM country WHERE codecountry = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codeCountry);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> readCountry(String codeCountry) {
        String query = "SELECT codecountry, namecountry FROM country WHERE codecountry = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, codeCountry);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Country country = new Country(rs.getString("codecountry"), rs.getString("namecountry"));
                    return Optional.of(country);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCountry(Country country, String code) {
        String query = "UPDATE country SET namecountry = ?, codecountry = ? WHERE codecountry = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, country.getNameCountry());
            ps.setString(2, country.getCodeCountry()); 
            ps.setString(3, code); 
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}