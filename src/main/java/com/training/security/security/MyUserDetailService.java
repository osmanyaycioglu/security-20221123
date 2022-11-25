package com.training.security.security;

import com.training.security.security.customer.data.dao.ICustomerDao;
import com.training.security.security.customer.services.models.Customer;
import com.training.security.security.identity.DataUsage;
import com.training.security.security.identity.IDataUsageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IDataUsageDao userDao;

    @Autowired
    private BCryptPasswordEncoder bc;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerDao.findByHashName(username.hashCode());
        if (customer != null) {
            return User.builder()
                       .username(customer.getCustomerCity())
                       .password(bc.encode(customer.getCustomerSalt()))
                       .roles("CUSTOMER")
                       .build();
        }
        DataUsage dataUsage = userDao.findById(username)
                                     .orElse(null);
        if (dataUsage == null) {
            throw new UsernameNotFoundException("auth problemi");
        }

        return User.builder()
                   .username(dataUsage.getDataName())
                   .password(bc.encode(dataUsage.getDataIdentity()))
                   .roles("" + dataUsage.getDataGroup())
                   .build();
    }
}

