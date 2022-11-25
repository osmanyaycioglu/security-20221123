package com.training.security.security.document.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.security.security.EncAttributeChanger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class DocumentRest {
    @Id
    @GeneratedValue
    private Long documentId;
    private int size;
    private String name;
    private String sum;
    @Convert(converter = EncAttributeChanger.class)
    private String docIdentity;
    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    private Long customerId;
}
