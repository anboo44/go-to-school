package com.uet.gts.core.model.vo.converter;

import com.uet.gts.core.model.vo.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null)
            return null;
        return gender.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return Gender.fromCode(dbData);
    }
}
