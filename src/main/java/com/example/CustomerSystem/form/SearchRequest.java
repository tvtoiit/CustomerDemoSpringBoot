package com.example.CustomerSystem.form;

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
}
