package com.nts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nts.validator.IdValidator;

@Target({ ElementType.FIELD, ElementType.METHOD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdValidator.class)
@Documented
public @interface ValidId {
	 String message() default "Invalid Id";

	  Class<?>[] groups() default { };

	  Class<? extends Payload>[] payload() default { };
	  
	  String name();
}
