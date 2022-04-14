package com.nts.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.nts.annotations.ValidName;

public class NameValidator implements ConstraintValidator<ValidName, String> {
	
	  private String name;
	  
	  @Value("${validation.name}")
	  private String validationRegex;

	  @Override
	  public void initialize(final ValidName obj) {
	    name=obj.name();
	  }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Must be a well formed "+name+".").addConstraintViolation();
		return (Pattern.matches(validationRegex,
				value))? true : false;
				}
		return true;
	}

}
