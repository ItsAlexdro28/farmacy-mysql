package com.farmacy.Modules.customer.aplication;

import com.farmacy.Modules.customer.domain.entity.Customer;
import com.farmacy.Modules.customer.domain.service.CustomerService;

public class UpdateCustomerUserCase {
    private CustomerService customerService;

    public UpdateCustomerUserCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer, String idCustomer) {
        customerService.updateCustomer(customer, idCustomer);
    }
}
