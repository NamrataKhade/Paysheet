package com.nts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nts.validator.NameValidator;

@Target({ ElementType.FIELD, ElementType.METHOD , ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface ValidName {
	 String message() default "Invalid Name";

	  Class<?>[] groups() default { };

	  Class<? extends Payload>[] payload() default { };
	  
	  String name();
}
