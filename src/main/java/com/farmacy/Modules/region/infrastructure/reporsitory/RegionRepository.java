package com.farmacy.Modules.region.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.region.domain.entity.Region;
import com.farmacy.Modules.region.domain.service.RegionService;

public class RegionRepository implements RegionService {
    private Connection connection;

    public RegionRepository() {
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
    public void createRegion(Region region) {
        String query = "INSERT INTO region (codereg, namereg, codecountry) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, region.getCodeReg());
            ps.setString(2, region.getNameReg());
            ps.setString(3, region.getCodeCountry());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Region> readRegion(String codeReg) {
        String query = "SELECT codereg, namereg, codecountry FROM region WHERE codereg = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeReg);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Region region = new Region(rs.getString("codereg"), rs.getString("namereg"), rs.getString("codecountry"));
                    return Optional.of(region);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateRegion(Region region, String codeReg) {
        String query = "UPDATE region SET codereg = ?, namereg = ?, codecountry = ? WHERE codereg = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, region.getCodeReg());
            ps.setString(2, region.getNameReg());
            ps.setString(3, region.getCodeCountry());
            ps.setString(4, codeReg);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRegion(String codeReg) {
        String query = "DELETE FROM region WHERE codereg = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeReg);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
