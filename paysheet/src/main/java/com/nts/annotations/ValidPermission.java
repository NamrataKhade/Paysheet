package com.nts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nts.validator.PermissionValidator;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PermissionValidator.class)
@Documented
public @interface ValidPermission {

	String message() default "Invalid permission";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
