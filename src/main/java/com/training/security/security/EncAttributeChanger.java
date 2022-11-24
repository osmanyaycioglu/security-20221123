package com.training.security.security;

import javax.persistence.AttributeConverter;

public class EncAttributeChanger implements AttributeConverter<String,String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return SecurityConfig.customOp.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return SecurityConfig.customOp.decrypt(dbData);
    }
}
