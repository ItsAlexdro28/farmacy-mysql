package com.farmacy.Modules.city.infrastructure.reporsitory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.city.domain.entity.City;
import com.farmacy.Modules.city.domain.service.CityService;

public class CityRepository implements CityService {
    private Connection connection;

    public CityRepository() {
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
    public void createCity(City city) {
        String query = "INSERT INTO city (codecity, namecity, codereg) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, city.getCodeCity());
            ps.setString(2, city.getNameCity());
            ps.setString(3, city.getCodeReg());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<City> readCity(String codeCity) {
        String query = "SELECT codecity, namecity, codereg FROM city WHERE codecity = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeCity);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    City city = new City(rs.getString("codecity"), rs.getString("namecity"), rs.getString("codereg"));
                    return Optional.of(city);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCity(City city, String codeCity) {
        String query = "UPDATE city SET codecity = ?, namecity = ?, codereg = ? WHERE codecity = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, city.getCodeCity());
            ps.setString(2, city.getNameCity());
            ps.setString(3, city.getCodeReg());
            ps.setString(4, codeCity);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCity(String codeCity) {
        String query = "DELETE FROM city WHERE codecity = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeCity);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}