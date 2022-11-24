package com.training.security.security.customer.data.dao;

import com.training.security.security.customer.services.models.Customer;
import com.training.security.security.customer.services.models.ECustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ICustomerDao extends JpaRepository<Customer,Long> {

    Customer findByCustomerUuid(String customerUuid);

    @Query("update Customer c set c.customerStatus=?2 where c.customerUuid=?1")
    @Modifying
    @Transactional
    void updateCustomerByUuid(String customerUuidParam,
                              ECustomerStatus deletedParam);
}
