package com.example.CustomerSystem.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

@Validated
public class LoginForm {
	 @NotEmpty(message = "Chưa nhập User ID")
	 private String userId;

	 @NotEmpty(message = "Chưa nhập Password")
	 private String passWord;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
