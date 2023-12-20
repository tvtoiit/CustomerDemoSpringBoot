package com.example.CustomerSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CustomerSystem.form.EditForm;
import com.example.CustomerSystem.form.EditResponse;
import com.example.CustomerSystem.service.EditService;

@Controller
public class EditController {
	@Autowired
	private EditService editService;
	
	@GetMapping("/edit")
	public String getInitEdit() {
		return "T003";
	}
	
	@GetMapping("/edit/{customerId}")
	public String editCustomer(@PathVariable int customerId, Model model) {
		EditResponse editResponse = editService.getCustomerById(customerId);
		model.addAttribute("editResponse", editResponse);
		return "T003";
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> edit(@Valid @ModelAttribute EditForm editForm) {
        editService.addCustomer(editForm);
		return ResponseEntity.ok("success");
	}
}
