package com.training.security.security.error.services;

import com.training.security.security.customer.data.CustomerDbOperations;
import com.training.security.security.customer.services.models.Customer;
import com.training.security.security.customer.services.models.ECustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

@Service
public class CustomerManagementService {
    @Autowired
    private CustomerDbOperations customerDbOperations;

    public String addCustomer(Customer customer) {
        Random random = new SecureRandom(UUID.randomUUID().toString().getBytes());
        String uuid = UUID.randomUUID().toString() + "-" + random.nextInt();
        customer.setCustomerUuid(uuid);
        customerDbOperations.insertCustomer(customer);
        return uuid;
    }

    public void deleteCustomer(String customerUuidParam) {
        Customer customer = customerDbOperations.search(customerUuidParam);
        if (customer != null){
            customerDbOperations.updateCustomerByUuid(customerUuidParam,
                                                      ECustomerStatus.DELETED);
        }
    }
}
