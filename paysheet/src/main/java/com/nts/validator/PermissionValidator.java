package com.nts.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.nts.annotations.ValidPermission;

public class PermissionValidator implements ConstraintValidator<ValidPermission, String> {

	  @Value("${validation.permissions}")
	  private String validationRegex;
	  
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			
		return (Pattern.matches(validationRegex,
				value))? true : false;
				}
		return true;
	}

}
