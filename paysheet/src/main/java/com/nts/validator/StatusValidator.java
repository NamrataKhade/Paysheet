package com.nts.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.nts.annotations.ValidStatus;
import com.nts.constants.Constants;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (StringUtils.isEmpty(value) &&
				(Constants.STATUS_ENABLED.equals(value) || Constants.STATUS_DISABLED.equals(value))) 
				? true : false;
	}

}
