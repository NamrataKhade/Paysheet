package com.nts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nts.validator.ValidLessThanDateValidator;

@Repeatable(value = ValidationOnDates.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidLessThanDateValidator.class)
@Documented
public @interface ValidLessThanDate {
	 String message() default "Invalid Dates";

	  Class<?>[] groups() default { };

	  Class<? extends Payload>[] payload() default { };
	  
	  String startDate();
	  String endDate();
}
