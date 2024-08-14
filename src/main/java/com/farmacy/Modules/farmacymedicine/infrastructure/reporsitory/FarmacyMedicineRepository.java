package com.farmacy.Modules.farmacymedicine.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.farmacymedicine.domain.entity.FarmacyMedicine;
import com.farmacy.Modules.farmacymedicine.domain.service.FarmacyMedicineService;

public class FarmacyMedicineRepository implements FarmacyMedicineService {
    private Connection connection;

    public FarmacyMedicineRepository() {
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
    public void createFarmacyMedicine(FarmacyMedicine farmacyMedicine) {
        String query = "INSERT INTO farmacymedicine (idfarmacy, idmedicinefatrm, price) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, farmacyMedicine.getIdFarmacy());
            ps.setInt(2, farmacyMedicine.getIdMedicineFarm());
            ps.setFloat(3, farmacyMedicine.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FarmacyMedicine> readFarmacyMedicine(int idFarmacy, int idMedicineFarm) {
        String query = "SELECT * FROM farmacymedicine WHERE idfarmacy = ? AND idmedicinefatrm = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idFarmacy);
            ps.setInt(2, idMedicineFarm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                FarmacyMedicine farmacyMedicine = new FarmacyMedicine(
                    rs.getInt("idfarmacy"),
                    rs.getInt("idmedicinefatrm"),
                    rs.getFloat("price")
                );
                return Optional.of(farmacyMedicine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateFarmacyMedicine(FarmacyMedicine farmacyMedicine, int idFarmacy, int idMedicineFarm) {
        String query = "UPDATE farmacymedicine SET price = ? WHERE idfarmacy = ? AND idmedicinefatrm = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setFloat(1, farmacyMedicine.getPrice());
            ps.setInt(2, idFarmacy);
            ps.setInt(3, idMedicineFarm);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFarmacyMedicine(int idFarmacy, int idMedicineFarm) {
        String query = "DELETE FROM farmacymedicine WHERE idfarmacy = ? AND idmedicinefatrm = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idFarmacy);
            ps.setInt(2, idMedicineFarm);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}