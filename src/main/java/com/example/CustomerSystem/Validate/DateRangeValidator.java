package com.example.CustomerSystem.Validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.CustomerSystem.form.EditForm;
import com.example.CustomerSystem.form.SearchRequest;

public class DateRangeValidator implements ConstraintValidator<DateRangeValdate, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
    	if (value == null) {
    		return true;
    	}
    	if (value instanceof SearchRequest) {
    		SearchRequest request = (SearchRequest) value;
    		System.out.println(request);
    		
    	}else if (value instanceof EditForm) {
    		 EditForm editForm = (EditForm) value;
    		 System.out.println(editForm);
    	}
    	
    	return true;
    }
        
}
