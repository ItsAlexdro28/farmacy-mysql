package com.farmacy.Modules.farmacy.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.farmacy.domain.entity.Farmacy;
import com.farmacy.Modules.farmacy.domain.service.FarmacyService;

public class FarmacyRepository implements FarmacyService {
    private Connection connection;

    public FarmacyRepository() {
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
    public void createFarmacy(Farmacy farmacy) {
        String query = "INSERT INTO farmacy (namefarmacy, addressfarmacy, longitude, latitude, codecityfarm, logofarmacy) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, farmacy.getNameFarmacy());
            ps.setString(2, farmacy.getAddressFarmacy());
            ps.setFloat(3, farmacy.getLongitude());
            ps.setFloat(4, farmacy.getLatitude());
            ps.setString(5, farmacy.getCodeCityFarm());
            ps.setString(6, farmacy.getLogoFarmacy());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Farmacy> readFarmacy(int idFarmacy) {
        String query = "SELECT * FROM farmacy WHERE idfarmacy = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idFarmacy);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Farmacy farmacy = new Farmacy(
                    rs.getInt("idfarmacy"),
                    rs.getString("namefarmacy"),
                    rs.getString("addressfarmacy"),
                    rs.getFloat("longitude"),
                    rs.getFloat("latitude"),
                    rs.getString("codecityfarm"),
                    rs.getString("logofarmacy")
                );
                return Optional.of(farmacy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateFarmacy(Farmacy farmacy, int idFarmacy) {
        String query = "UPDATE farmacy SET namefarmacy = ?, addressfarmacy = ?, longitude = ?, latitude = ?, codecityfarm = ?, logofarmacy = ? WHERE idfarmacy = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, farmacy.getNameFarmacy());
            ps.setString(2, farmacy.getAddressFarmacy());
            ps.setFloat(3, farmacy.getLongitude());
            ps.setFloat(4, farmacy.getLatitude());
            ps.setString(5, farmacy.getCodeCityFarm());
            ps.setString(6, farmacy.getLogoFarmacy());
            ps.setInt(7, idFarmacy);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFarmacy(int idFarmacy) {
        String query = "DELETE FROM farmacy WHERE idfarmacy = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idFarmacy);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}