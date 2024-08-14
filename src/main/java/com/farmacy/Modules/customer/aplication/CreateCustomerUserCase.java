package com.farmacy.Modules.customer.aplication;
import com.farmacy.Modules.customer.domain.entity.Customer;
import com.farmacy.Modules.customer.domain.service.CustomerService;

public class CreateCustomerUserCase {
    private CustomerService customerService;

    public CreateCustomerUserCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer) {
        customerService.createCustomer(customer);
    }
}

