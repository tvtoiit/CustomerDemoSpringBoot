package com.example.CustomerSystem.Validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            if (request.getBirthdayFrom() != null && request.getBirthdayTo() != null) {
                if (!isValidDate(request.getBirthdayFrom()) || !isValidDate(request.getBirthdayTo())) {
                    context.buildConstraintViolationWithTemplate("Invalid date format. Use YYYY/MM/DD.")
                           .addPropertyNode("birthdayFrom")
                           .addConstraintViolation();
                    return false;
                }
            }
        } else if (value instanceof EditForm) {
            // Xử lý cho EditForm tương tự như trên
            EditForm editForm = (EditForm) value;
            // Thêm logic kiểm tra cho EditForm nếu cần
        }

        return true;
	}

	private boolean isDateAfter(String date1, String date2) {
        try {
            LocalDate localDate1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            LocalDate localDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            return localDate1.isAfter(localDate2);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidDate(String date) {
        try {
            // Sử dụng DateTimeFormatter để kiểm tra định dạng
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
        
}
