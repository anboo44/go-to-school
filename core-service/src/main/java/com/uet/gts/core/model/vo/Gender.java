package com.uet.gts.core.model.vo;


import com.uet.gts.common.util.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Gender implements Code<String> {
    MALE("male"), FEMALE("female");

    @Getter
    private final String code;

    public static Gender fromCode(String code) {
        return Code.fromCode(code, Gender.values());
    }
}
