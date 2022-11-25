package com.training.security.security.customer.data;

import com.training.security.security.customer.data.dao.ICustomerDao;
import com.training.security.security.customer.services.models.Customer;
import com.training.security.security.customer.services.models.ECustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDbOperations {

    @Autowired
    private ICustomerDao customerDao;

    public void insertCustomer(Customer customerParam){
        customerDao.save(customerParam);
    }

    public Customer search(String customerUuidParam) {
        return customerDao.findByCustomerUuid(customerUuidParam);
    }

    public void updateCustomerByUuid(String customerUuidParam,
                                     ECustomerStatus deletedParam) {
        customerDao.updateCustomerByUuid(customerUuidParam,deletedParam);
    }
}
