package com.uet.gts.auth.model.enumeration;

import com.uet.gts.common.util.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum UserRole implements Code<Integer>, GrantedAuthority {

    ADMIN(1),
    MANAGER(2),
    USER(3);

    @Getter
    private final Integer code;

    public static UserRole fromCode(Integer code) {
        return Code.fromCode(code, UserRole.values());
    }

    public static UserRole fromCode(Integer code, UserRole defaultValue) {
        return Code.fromCode(code, UserRole.values(), defaultValue);
    }

    public String getAuthority() {
        return name().toLowerCase();
    }
}
