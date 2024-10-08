package com.farmacy.Modules.laboratory.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.laboratory.domain.entity.Laboratory;
import com.farmacy.Modules.laboratory.domain.service.LaboratoryService;

public class LaboratoryRepository implements LaboratoryService {
    private Connection connection;

    public LaboratoryRepository() {
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
    public void createLaboratory(Laboratory laboratory) {
        String query = "INSERT INTO laboratory (namelab, codecityreg) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, laboratory.getNameLab());
            ps.setString(2, laboratory.getCodeCityReg());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Laboratory> readLaboratory(int id) {
        String query = "SELECT id, namelab, codecityreg FROM laboratory WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Laboratory laboratory = new Laboratory(rs.getInt("id"), rs.getString("namelab"), rs.getString("codecityreg"));
                    return Optional.of(laboratory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateLaboratory(Laboratory laboratory, int id) {
        String query = "UPDATE laboratory SET namelab = ?, codecityreg = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, laboratory.getNameLab());
            ps.setString(2, laboratory.getCodeCityReg());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLaboratory(int id) {
        String query = "DELETE FROM laboratory WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
