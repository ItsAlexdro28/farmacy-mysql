package com.farmacy.Modules.medicine.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.medicine.domain.entity.Medicine;
import com.farmacy.Modules.medicine.domain.service.MedicineService;

public class MedicineRepository implements MedicineService {
    private Connection connection;

    public MedicineRepository() {
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
    public void createMedicine(Medicine medicine) {
        String query = "INSERT INTO medicine (proceedings, namemedicine, healthregister, description, descriptionshort, codemodeadmin, codeap, codeum, namerol, codelab) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, medicine.getProceedings());
            ps.setString(2, medicine.getNameMedicine());
            ps.setString(3, medicine.getHealthRegister());
            ps.setString(4, medicine.getDescription());
            ps.setString(5, medicine.getDescriptionShort());
            ps.setInt(6, medicine.getCodeModeAdmin());
            ps.setInt(7, medicine.getCodeAp());
            ps.setInt(8, medicine.getCodeUm());
            ps.setString(9, medicine.getNameRol());
            ps.setInt(10, medicine.getCodeLab());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Medicine> readMedicine(int id) {
        String query = "SELECT * FROM medicine WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Medicine medicine = new Medicine(
                    rs.getInt("id"),
                    rs.getString("proceedings"),
                    rs.getString("namemedicine"),
                    rs.getString("healthregister"),
                    rs.getString("description"),
                    rs.getString("descriptionshort"),
                    rs.getInt("codemodeadmin"),
                    rs.getInt("codeap"),
                    rs.getInt("codeum"),
                    rs.getString("namerol"),
                    rs.getInt("codelab")
                );
                return Optional.of(medicine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateMedicine(Medicine medicine, int id) {
        String query = "UPDATE medicine SET proceedings = ?, namemedicine = ?, healthregister = ?, description = ?, descriptionshort = ?, codemodeadmin = ?, codeap = ?, codeum = ?, namerol = ?, codelab = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, medicine.getProceedings());
            ps.setString(2, medicine.getNameMedicine());
            ps.setString(3, medicine.getHealthRegister());
            ps.setString(4, medicine.getDescription());
            ps.setString(5, medicine.getDescriptionShort());
            ps.setInt(6, medicine.getCodeModeAdmin());
            ps.setInt(7, medicine.getCodeAp());
            ps.setInt(8, medicine.getCodeUm());
            ps.setString(9, medicine.getNameRol());
            ps.setInt(10, medicine.getCodeLab());
            ps.setInt(11, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMedicine(int id) {
        String query = "DELETE FROM medicine WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
