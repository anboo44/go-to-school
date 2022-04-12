package com.uet.gts.core.model.vo.converter;

import com.uet.gts.core.model.vo.GroupType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupTypeConverter implements AttributeConverter<GroupType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(GroupType groupType) {
        if (groupType == null)
            return null;
        return groupType.getCode();
    }

    @Override
    public GroupType convertToEntityAttribute(Integer dbData) {
       if (dbData == null)
           return null;
        return GroupType.fromCode(dbData);
    }
}
