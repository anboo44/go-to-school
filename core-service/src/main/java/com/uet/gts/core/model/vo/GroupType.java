package com.uet.gts.core.model.vo;

import com.uet.gts.core.model.base.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GroupType implements Code<Integer> {
    NORMAL(0), SPECIAL(1);

    @Getter
    private final Integer code;

    public static GroupType fromCode(Integer code) {
        return Code.fromCode(code, GroupType.values());
    }

    public static GroupType fromValue(String v) {
        if (v == null)
            return null;
        return GroupType.valueOf(v.toUpperCase());
    }
}
