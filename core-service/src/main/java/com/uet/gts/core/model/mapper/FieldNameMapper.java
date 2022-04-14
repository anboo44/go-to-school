package com.uet.gts.core.model.mapper;

import com.uet.gts.core.model.entity.Student;

import java.util.Map;
import java.util.Optional;

public class FieldNameMapper {
    private static final Map<Class, Map<String, String>> fieldMapper = Map.of(
            Student.class, Map.of("idx", "id")
    );

    public static Optional<String> mappedValue(Object obj, String raw) {
        try {
            return Optional.of(
                    fieldMapper.get(obj.getClass()).get(raw)
            );
        } catch (Throwable e) {
            return Optional.empty();
        }
    }
}
