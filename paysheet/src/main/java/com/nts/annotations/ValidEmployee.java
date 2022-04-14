package com.nts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nts.validator.UserValidator;

@Target({ ElementType.FIELD, ElementType.METHOD , ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
@Documented
public @interface ValidEmployee {
	
	 String message() default "Invalid employee name";

	  Class<?>[] groups() default { };

	  Class<? extends Payload>[] payload() default { };
	  
	  String name();
}
