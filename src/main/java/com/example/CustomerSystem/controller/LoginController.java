package com.example.CustomerSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.CustomerSystem.entity.MstUser;
import com.example.CustomerSystem.form.LoginForm;
import com.example.CustomerSystem.service.LoginService;

@Controller
@Validated
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping(value = {"/", "/login"})
	public String inIt() {
		return "Login";
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<String> login(@ModelAttribute LoginForm form) {
	        MstUser result = loginService.getUser(form.getUserId(), form.getPassWord());
	        if (result != null) {
	            return ResponseEntity.ok("success");
	        } else {
	            return ResponseEntity.badRequest().body("Sai tai khoan va mat khau");
	        }
	    }
}