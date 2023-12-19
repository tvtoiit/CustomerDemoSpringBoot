package com.example.CustomerSystem.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.CustomerSystem.Validate.DateRangeValdate;

@DateRangeValdate
public class SearchRequest {
	private String name;
	private String sex;
	private String birthdayFrom;
	private String birthdayTo;
	private int page;
	private String sMode;
	private String[] selectedValues;

	public String[] getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(String[] selectedValues) {
		this.selectedValues = selectedValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthdayFrom() {
		return birthdayFrom;
	}

	public void setBirthdayFrom(String birthdayFrom) {
		this.birthdayFrom = birthdayFrom;
	}

	public String getBirthdayTo() {
		return birthdayTo;
	}

	public void setBirthdayTo(String birthdayTo) {
		this.birthdayTo = birthdayTo;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getsMode() {
		return sMode;
	}

	public void setsMode(String sMode) {
		this.sMode = sMode;
	}
	
	public boolean isDateRangeValid() {
        if (birthdayFrom == null || birthdayTo == null) {
            return true; 
        }

        try {
            LocalDate from = LocalDate.parse(birthdayFrom, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            LocalDate to = LocalDate.parse(birthdayTo, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            // Kiểm tra nếu birthdayFrom > birthdayTo
            if (from.isAfter(to)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false; // Bỏ qua nếu có lỗi chuyển đổi
        }
    }
}
