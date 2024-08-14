package com.farmacy.Modules.modeadministration.infrastructure.reporsitory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.modeadministration.domain.entity.ModeAdministration;
import com.farmacy.Modules.modeadministration.domain.service.ModeAdministrationService;

public class ModeAdministrationRepository implements ModeAdministrationService {
    private Connection connection;

    public ModeAdministrationRepository() {
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
    public void createModeAdministration(ModeAdministration modeAdministration) {
        String query = "INSERT INTO modeadministration (descriptionmode) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, modeAdministration.getDescriptionMode());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ModeAdministration> readModeAdministration(int id) {
        String query = "SELECT id, descriptionmode FROM modeadministration WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ModeAdministration modeAdministration = new ModeAdministration(
                        rs.getInt("id"),
                        rs.getString("descriptionmode")
                    );
                    return Optional.of(modeAdministration);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateModeAdministration(ModeAdministration modeAdministration, int id) {
        String query = "UPDATE modeadministration SET descriptionmode = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, modeAdministration.getDescriptionMode());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteModeAdministration(int id) {
        String query = "DELETE FROM modeadministration WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}