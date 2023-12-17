package com.example.CustomerSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.CustomerSystem.form.SearchRequest;
import com.example.CustomerSystem.service.DeleteService;

@Controller
public class DeleteController {
	@Autowired
	private DeleteService deleteService;
	
	@PostMapping("/Delete")
	public String softDeleteCustomers(@RequestBody SearchRequest searchForm) {
		String[] selecValue = searchForm.getSelectedValues();
	    deleteService.softDeleteCustomers(selecValue);
	    return "Search";
    } 
}
