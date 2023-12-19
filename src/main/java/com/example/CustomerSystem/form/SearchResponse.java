package com.example.CustomerSystem.form;

import java.util.List;

import com.example.CustomerSystem.entity.MstCustomer;

public class SearchResponse {
	private List<MstCustomer> dataSearch;
	private int page;
	private String name;
	private String sex;
	private String birthDayFrom;
	private String birthDayTo;
	
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
	public String getBirthDayFrom() {
		return birthDayFrom;
	}
	public void setBirthDayFrom(String birthDayFrom) {
		this.birthDayFrom = birthDayFrom;
	}
	public String getBirthDayTo() {
		return birthDayTo;
	}
	public void setBirthDayTo(String birthDayTo) {
		this.birthDayTo = birthDayTo;
	}
	public List<MstCustomer> getDataSearch() {
		return dataSearch;
	}
	public void setDataSearch(List<MstCustomer> dataSearch) {
		this.dataSearch = dataSearch;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
