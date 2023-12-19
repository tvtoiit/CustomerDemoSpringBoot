package com.example.CustomerSystem.Validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.CustomerSystem.form.SearchRequest;

public class DateRangeValidator implements ConstraintValidator<DateRangeValdate, SearchRequest> {

    @Override
    public boolean isValid(SearchRequest request, ConstraintValidatorContext context) {
        if (request.getBirthdayFrom() != null && request.getBirthdayTo() != null) {
            // Kiểm tra định dạng ngày tháng và so sánh
            // Nếu có lỗi, set message và trả về false
            if (!isValidDate(request.getBirthdayFrom()) || !isValidDate(request.getBirthdayTo())) {
                context.buildConstraintViolationWithTemplate("Invalid date format. Use YYYY/MM/DD.")
                       .addPropertyNode("birthdayFrom")
                       .addConstraintViolation();
                return false;
            }

            // Kiểm tra nếu birthdayFrom > birthdayTo
            if (isDateAfter(request.getBirthdayFrom(), request.getBirthdayTo())) {
                context.buildConstraintViolationWithTemplate("Invalid date range. birthdayFrom must be before birthdayTo.")
                       .addPropertyNode("birthdayFrom")
                       .addConstraintViolation();
                return false;
            }
        }
        // Nếu không có lỗi nào, trả về true
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
