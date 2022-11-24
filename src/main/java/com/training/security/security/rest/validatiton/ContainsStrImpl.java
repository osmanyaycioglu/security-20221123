package com.training.security.security.rest.validatiton;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContainsStrImpl implements ConstraintValidator<ContainsStr,String> {

    private ContainsStr anno;
    @Override
    public void initialize(ContainsStr constraintAnnotation) {
        anno = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {
        String[] values = anno.value();
        for (String s : values) {
            if (value.contains(s)){
                return false;
            }
        }
        return true;
    }
}
