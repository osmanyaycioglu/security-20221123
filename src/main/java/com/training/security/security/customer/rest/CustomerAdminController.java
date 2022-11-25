package com.training.security.security.customer.rest;

import com.training.security.security.customer.rest.mappings.ICustomerMappings;
import com.training.security.security.customer.rest.model.CustomerRest;
import com.training.security.security.error.services.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/customer")
@RequestMapping("/admin/v1/customer/management")
public class CustomerAdminController {
    @Autowired
    private CustomerManagementService customerManagementService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody @Valid CustomerRest customerRestParam) {
        return customerManagementService.addCustomer(ICustomerMappings.customerMappings.toCustomer(customerRestParam));
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("cid") String customerUuid) {
        customerManagementService.deleteCustomer(customerUuid);
        return "OK";
    }


}
