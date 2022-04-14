package com.nts.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.nts.annotations.ValidEmployee;

public class UserValidator  implements ConstraintValidator<ValidEmployee, String> {
	
	  private String name;
	  
	  @Value("${validation.user}")
	  private String validationRegex;

	  @Override
	  public void initialize(final ValidEmployee obj) {
	    name=obj.name();
	  }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Must be a well formed "+name+" Name.").addConstraintViolation();
		return (Pattern.matches(validationRegex,
				value))? true : false;
				}
		return true;
	}

}
