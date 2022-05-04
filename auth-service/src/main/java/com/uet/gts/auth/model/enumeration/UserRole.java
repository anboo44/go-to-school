package com.uet.gts.auth.model.enumeration;

import com.uet.gts.common.util.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum UserRole implements Code<Integer>, GrantedAuthority {

    ROLE_ADMIN(1),
    ROLE_MANAGER(2),
    ROLE_USER(3);

    @Getter
    private final Integer code;

    public static UserRole fromCode(Integer code) {
        return Code.fromCode(code, UserRole.values());
    }

    public static UserRole fromCode(Integer code, UserRole defaultValue) {
        return Code.fromCode(code, UserRole.values(), defaultValue);
    }

    public String getAuthority() {
        return name();
    }
}
