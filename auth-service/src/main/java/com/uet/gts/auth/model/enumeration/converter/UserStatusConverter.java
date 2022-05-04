package com.uet.gts.auth.model.enumeration.converter;

import com.uet.gts.auth.model.enumeration.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus status) {
        if (status == null)
            return null;
        return status.getCode();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer code) {
        if (code == null)
            return null;
        return UserStatus.fromCode(code);
    }
}
