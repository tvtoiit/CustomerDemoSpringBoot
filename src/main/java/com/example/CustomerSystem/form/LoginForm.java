package com.example.CustomerSystem.form;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

@Validated
public class LoginForm {
	@NotBlank(message = "Chưa nhập User ID")
	 private String userId;

	@NotBlank(message = "Chưa nhập Password")
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
