package com.nts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nts.validator.IsValidDateValidator;

@Target({ ElementType.FIELD, ElementType.METHOD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsValidDateValidator.class)
@Documented
public @interface IsValidDate {
	
	String message() default "Invalid Date";

	  Class<?>[] groups() default { };

	  Class<? extends Payload>[] payload() default { };
}
