package com.training.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeRun {

    private static Logger logger = LoggerFactory.getLogger(EmployeeRun.class);

    public static void main(String[] args) {
        Employee employee = Employee.builder()
                                    .withName("osman")
                                    .withSurname("yaycıoğlu")
                                    .withUsername("osmany")
                                    .withPassword("123456786")
                                    .build();
        // hata 1
        logger.debug("emp : " +  employee);
        logger.trace("emp : " +  employee);
        // hata 2
        System.out.println("emp : " +  employee);

    }

}
