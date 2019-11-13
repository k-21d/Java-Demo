package com.k21d.spring.springbootbeanvalidation.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 合法卡号校验
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidCardNumberConstraintValidator.class})
public @interface ValidCardNumber {
    String message() default "{com.k21d.spring.springbootbeanvalidation.validation.invalid.card.number.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
