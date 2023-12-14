package com.example.CustomerSystem.form;

public class SearchRespone {
	private int customerId;
	private String customerName;
	private String sex;
	private String birthDay;
	private String email;
	private String address;
	private String page;
	private boolean statusBtn = false;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public boolean getStatusBtn() {
		return statusBtn;
	}
	public void setStatusBtn(boolean statusBtn) {
		this.statusBtn = statusBtn;
	}
}
