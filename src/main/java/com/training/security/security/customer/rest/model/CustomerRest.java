package com.training.security.security.customer.rest.model;

import com.training.security.security.rest.validatiton.ContainsStr;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CustomerRest {
    @NotEmpty
    @Size(min=2,max=20)
    private   String name;
    @NotEmpty
    @Size(min=3,max=25)
    private   String surname;
    @NotEmpty
    @Size(min=8,max=20)
    private   String username;
    // one lowercase, one uppercase and one special character
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    @ContainsStr({"123","1234","abc","qwer","qwerty"})
    protected String password;
}
