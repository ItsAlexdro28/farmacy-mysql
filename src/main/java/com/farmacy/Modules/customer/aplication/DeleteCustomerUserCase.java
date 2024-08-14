package com.farmacy.Modules.customer.aplication;

import com.farmacy.Modules.customer.domain.service.CustomerService;

public class DeleteCustomerUserCase {
    private CustomerService customerService;

    public DeleteCustomerUserCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(String idCustomer) {
        customerService.deleteCustomer(idCustomer);
    }
    
}
