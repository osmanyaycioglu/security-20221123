package com.training.security.security.customer.rest.mappings;

import com.training.security.security.customer.rest.model.CustomerRest;
import com.training.security.security.customer.services.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICustomerMappings {

    ICustomerMappings customerMappings = Mappers.getMapper(ICustomerMappings.class);

    @Mapping(source = "username", target = "customerCity")
    @Mapping(source = "password", target = "customerSalt")
    Customer toCustomer(CustomerRest customerParam);

    @Mapping(source = "customerCity", target = "username")
    CustomerRest toCustomerRest(Customer customerParam);

}
