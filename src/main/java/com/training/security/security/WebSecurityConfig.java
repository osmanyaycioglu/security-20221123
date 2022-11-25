package com.training.security.security;

import com.training.security.security.filter.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurityParam,
                                                       MyUserDetailService myUserDetailServiceParam,
                                                       BCryptPasswordEncoder encoderParam) throws Exception {
        return httpSecurityParam.getSharedObject(AuthenticationManagerBuilder.class)
                                .userDetailsService(myUserDetailServiceParam)
                                .passwordEncoder(encoderParam)
                                .and()
                                .build();
    }

    @Bean
    public SecurityFilterChain localSecurity(HttpSecurity httpSecurityParam,
                                             JWTFilter jwtFilterParam) throws Exception {
        return httpSecurityParam.authorizeRequests()
                                .antMatchers("/actuator/**",
                                             "/v3/**",
                                             "/auth/**")
                                .anonymous()
                                .antMatchers("/admin/**",
                                             "/intl/**",
                                             "/config/**")
                                .hasRole("ADMIN")

                                .antMatchers("/api/**")
                                .hasRole("CUSTOMER")
                                .and()
                                .httpBasic()
                                .disable()
                                .csrf()
                                .disable()
                                .cors()
                                .disable()
                                .formLogin()
                                .disable()
                                .addFilterBefore(jwtFilterParam,
                                                 UsernamePasswordAuthenticationFilter.class)
                                .build();
    }
}
