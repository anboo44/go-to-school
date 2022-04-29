package com.uet.gts.core.common.util.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GreaterThan0Validator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface GreaterThan0 {
    String message() default "Id must be greater than 0";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
