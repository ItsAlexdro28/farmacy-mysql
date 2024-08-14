package com.farmacy.Modules.unitmeasurement.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.unitmeasurement.domain.entity.UnitMeasurement;
import com.farmacy.Modules.unitmeasurement.domain.service.UnitMeasurementService;

public class UnitMeasurementRepository implements UnitMeasurementService {
    private Connection connection;

    public UnitMeasurementRepository() {
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
    public void createUnitMeasurement(UnitMeasurement unitMeasurement) {
        String query = "INSERT INTO unitmeasurement (nameum) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, unitMeasurement.getNameUm());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<UnitMeasurement> readUnitMeasurement(int idUm) {
        String query = "SELECT idum, nameum FROM unitmeasurement WHERE idum = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idUm);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UnitMeasurement unitMeasurement = new UnitMeasurement(rs.getInt("idum"), rs.getString("nameum"));
                    return Optional.of(unitMeasurement);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateUnitMeasurement(UnitMeasurement unitMeasurement, int idUm) {
        String query = "UPDATE unitmeasurement SET nameum = ? WHERE idum = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, unitMeasurement.getNameUm());
            ps.setInt(2, idUm);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUnitMeasurement(int idUm) {
        String query = "DELETE FROM unitmeasurement WHERE idum = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idUm);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}