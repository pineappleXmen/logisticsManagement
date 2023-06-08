package com.pineapple.pineapplelogistics.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 19:49 2023/4/23
 * @Modifier by:
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneValidator.class})
public @interface Phone {

    boolean required() default true;

    String message() default "Phone format error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
