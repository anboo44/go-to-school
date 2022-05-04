package com.uet.gts.auth.model.enumeration.converter;

import com.uet.gts.auth.model.enumeration.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null)
            return null;
        return userRole.getCode();
    }

    @Override
    public UserRole convertToEntityAttribute(Integer code) {
        if (code == null)
            return null;
        return UserRole.fromCode(code);
    }
}
