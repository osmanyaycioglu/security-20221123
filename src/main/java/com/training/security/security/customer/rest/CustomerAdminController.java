package com.training.security.security.customer.rest;

import com.training.security.security.customer.rest.model.CustomerRest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/customer")
@RequestMapping("/admin/v1/customer/management")
public class CustomerAdminController {

    @PostMapping("/add")
    public String addCustomer(@RequestBody @Valid CustomerRest customerRestParam){

        return "OK";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("cid") Long customerId){

        return "OK";
    }


}
