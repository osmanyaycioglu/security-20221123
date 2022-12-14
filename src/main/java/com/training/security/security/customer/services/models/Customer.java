package com.training.security.security.customer.services.models;

import com.training.security.security.EncAttributeChanger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
public class Customer {
    @Id
    @GeneratedValue
    private Long            customerId;
    @NotEmpty
    private String          customerUuid;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String          name;
    @NotEmpty
    @Size(min = 3, max = 25)
    private String          surname;
    @Convert(converter = EncAttributeChanger.class)
    private String          customerCity;
    @Convert(converter = EncAttributeChanger.class)
    private String          customerSalt;
    @Enumerated(EnumType.STRING)
    private ECustomerStatus customerStatus = ECustomerStatus.ACTIVE;
    private LocalDateTime   addDate;
    private LocalDateTime   updateDate;
    @Column(unique = true)
    private int             hashName;

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
        hashName = customerCity.hashCode();
    }

    @PrePersist
    public void preInsert() {
        addDate = LocalDateTime.now();
        hashName = customerCity.hashCode();
    }

}
