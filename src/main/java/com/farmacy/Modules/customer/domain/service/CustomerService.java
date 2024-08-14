package com.farmacy.Modules.customer.domain.service;

import java.util.Optional;

import com.farmacy.Modules.customer.domain.entity.Customer;

public interface CustomerService {
    void createCustomer(Customer customer);
    Optional<Customer> readCustomer(String idCustomer);
    void updateCustomer(Customer customer, String idCustomer);
    void deleteCustomer(String idCustomer);
}
