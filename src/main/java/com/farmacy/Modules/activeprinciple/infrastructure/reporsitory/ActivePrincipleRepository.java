package com.farmacy.Modules.activeprinciple.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.activeprinciple.domain.entity.ActivePrinciple;
import com.farmacy.Modules.activeprinciple.domain.service.ActivePrincipleService;

public class ActivePrincipleRepository implements ActivePrincipleService {
    private Connection connection;

    public ActivePrincipleRepository() {
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
    public void createActivePrinciple(ActivePrinciple activePrinciple) {
        String query = "INSERT INTO activeprinciple (nameap) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, activePrinciple.getNameAp());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ActivePrinciple> readActivePrinciple(int idAp) {
        String query = "SELECT idap, nameap FROM activeprinciple WHERE idap = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idAp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ActivePrinciple activePrinciple = new ActivePrinciple(rs.getInt("idap"), rs.getString("nameap"));
                    return Optional.of(activePrinciple);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateActivePrinciple(ActivePrinciple activePrinciple, int idAp) {
        String query = "UPDATE activeprinciple SET nameap = ? WHERE idap = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, activePrinciple.getNameAp());
            ps.setInt(2, idAp);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActivePrinciple(int idAp) {
        String query = "DELETE FROM activeprinciple WHERE idap = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idAp);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}