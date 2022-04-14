package com.nts.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nts.annotations.IsValidDate;

public class IsValidDateValidator implements ConstraintValidator<IsValidDate, Date> {

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		boolean returnVal = false;
		if (null != value) {
//		        String[] permissFormats = new String[]{"MM/dd/yyyy hh:mm:ss.sss","MM/dd/yyyy hh:mm:ss","yyyy-MM-dd hh:mm:ss.sss","yyyy-MM-dd hh:mm","dd/MM/yyyy",
//		                "dd-MM-yyyy","MM-dd-yyyy","ddMMyyyy"};

			String[] permissFormats = new String[] { "yyyy-MM-dd hh:mm:ss", "yyyy-mm-dd", "MM/dd/yyyy" };

			for (String format : permissFormats) {
				try {
					SimpleDateFormat sdfObj = new SimpleDateFormat(format);
//		                sdfObj.setLenient(false);
					sdfObj.format(value);
					returnVal = true;
					break;
				} catch (Exception e) {
					returnVal = false;
				}
			}
			return returnVal;
		}
		return true;
	}

}
