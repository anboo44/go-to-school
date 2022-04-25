package com.uet.gts.core.repository.base;

import com.uet.gts.core.model.mapper.FieldNameMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class JpaCustomSort {

    private final Pattern ASC_PATTERN = Pattern.compile("(\\w+)ASC$");
    private final Pattern DESC_PATTERN = Pattern.compile("(\\w+)DES$");
    private final Integer FIRST_REGEX_GROUP = 1;

    public <T> Sort buildSortStatement(T obj, String param) {
        if (param == null) return Sort.unsorted();

        var ascMatcher = ASC_PATTERN.matcher(param);
        var descMatcher = DESC_PATTERN.matcher(param);

        if (ascMatcher.find()) {
            var extractedName = ascMatcher.group(FIRST_REGEX_GROUP);
            var fieldName = FieldNameMapper.mappedValue(obj, extractedName).orElse(extractedName);

            if (isValidField(obj, fieldName))
                return Sort.by(Sort.Direction.ASC, fieldName);
        }
        if (descMatcher.find()) {
            var extractedName = descMatcher.group(FIRST_REGEX_GROUP);
            var fieldName = FieldNameMapper.mappedValue(obj, extractedName).orElse(extractedName);

            if (isValidField(obj, fieldName))
                return Sort.by(Sort.Direction.DESC, fieldName);
        }

        return Sort.unsorted();
    }

    private <T> boolean isValidField(T obj, String fieldName) {
        var fieldNames = Arrays.stream(
                obj.getClass().getDeclaredFields()
        ).map(Field::getName).collect(Collectors.toList());

        return fieldNames.contains(fieldName);
    }
}
