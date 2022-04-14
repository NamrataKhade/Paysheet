package com.nts.validator;

import java.lang.reflect.Field;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nts.annotations.ValidLessThanDate;

public class ValidLessThanDateValidator implements ConstraintValidator<ValidLessThanDate, Object> {
 
    private String feildStartDate;
    private String feildEndDate;
 
    @Override
    public void initialize(ValidLessThanDate constraint) {
    	feildStartDate = constraint.startDate();
    	feildEndDate = constraint.endDate();
    }
 
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            Date startDate = (Date) getFieldValue(object, feildStartDate);
            Date endDate = (Date) getFieldValue(object, feildEndDate);

        	 
            return endDate.after(startDate) || startDate.equals(endDate);
        } catch (Exception e) {
            // log error
            return false;
        }
    }
 
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field date = clazz.getDeclaredField(fieldName);
        date.setAccessible(true);
        return date.get(object);
    }
}
