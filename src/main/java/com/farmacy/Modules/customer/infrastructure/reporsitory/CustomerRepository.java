package com.farmacy.Modules.customer.infrastructure.reporsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;

import com.farmacy.Modules.customer.domain.entity.Customer;
import com.farmacy.Modules.customer.domain.service.CustomerService;

public class CustomerRepository implements CustomerService {
    private Connection connection;

    public CustomerRepository() {
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
    public void createCustomer(Customer customer) {
        String query = "INSERT INTO customer (idcustomer, namecustomer, lastnamecustomer, codecitycustomer, emailcustomer, birthdate, longitude, latitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, customer.getIdCustomer());
            ps.setString(2, customer.getNameCustomer());
            ps.setString(3, customer.getLastNameCustomer());
            ps.setString(4, customer.getCodeCityCustomer());
            ps.setString(5, customer.getEmailCustomer());
            ps.setString(6, customer.getBirthDate());
            ps.setFloat(7, customer.getLongitude());
            ps.setFloat(8, customer.getLatitude());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> readCustomer(String idCustomer) {
        String query = "SELECT * FROM customer WHERE idcustomer = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, idCustomer);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer(
                    rs.getString("idcustomer"),
                    rs.getString("namecustomer"),
                    rs.getString("lastnamecustomer"),
                    rs.getString("codecitycustomer"),
                    rs.getString("emailcustomer"),
                    rs.getString("birthdate"),
                    rs.getFloat("longitude"),
                    rs.getFloat("latitude")
                );
                return Optional.of(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCustomer(Customer customer, String idCustomer) {
        String query = "UPDATE customer SET idcustomer = ?, namecustomer = ?, lastnamecustomer = ?, codecitycustomer = ?, emailcustomer = ?, birthdate = ?, longitude = ?, latitude = ? WHERE idcustomer = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, customer.getIdCustomer());
            ps.setString(2, customer.getNameCustomer());
            ps.setString(3, customer.getLastNameCustomer());
            ps.setString(4, customer.getCodeCityCustomer());
            ps.setString(5, customer.getEmailCustomer());
            ps.setString(6, customer.getBirthDate());
            ps.setFloat(7, customer.getLongitude());
            ps.setFloat(8, customer.getLatitude());
            ps.setString(9, idCustomer);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(String idCustomer) {
        String query = "DELETE FROM customer WHERE idcustomer = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, idCustomer);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
