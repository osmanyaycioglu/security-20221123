package com.training.security.security.identity;

import com.training.security.security.EncAttributeChanger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class DataUsage {
    @Id
    private String dataName;
    @Convert(converter = EncAttributeChanger.class)
    private String dataIdentity;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERole dataGroup;
}
