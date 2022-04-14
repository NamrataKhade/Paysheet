package com.nts.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.nts.annotations.ValidId;

public class IdValidator implements ConstraintValidator<ValidId, String> {

	private String name;

	@Override
	public void initialize(final ValidId obj) {
		name = obj.name();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Must be a well formed " + name + "Id.").addConstraintViolation();
			return (Pattern.matches("^[a-zA-Z0-9]+$", value)) ? true : false;
		}
		return true;
	}

}
