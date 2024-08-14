package com.farmacy.Modules.customer.aplication;


import java.util.Optional;

import com.farmacy.Modules.customer.domain.entity.Customer;
import com.farmacy.Modules.customer.domain.service.CustomerService;

public class ReadCustomerUserCase {
    private CustomerService customerService;

    public ReadCustomerUserCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Optional<Customer> execute(String idCustomer) {
        return customerService.readCustomer(idCustomer);
    }
}