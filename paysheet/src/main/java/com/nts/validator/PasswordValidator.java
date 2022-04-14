package com.nts.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.nts.annotations.ValidPassword;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			
		return (Pattern.matches("^[a-zA-z0-9]+$",
				value))? true : false;
				}
		return true;
	}

}
