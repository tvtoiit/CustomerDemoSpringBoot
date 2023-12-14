package com.example.CustomerSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.CustomerSystem.form.SearchForm;
import com.example.CustomerSystem.form.SearchRespone;
import com.example.CustomerSystem.service.SearchService;

@Controller

public class SearchController {

    @Autowired
    private SearchService searchService;
    
    @GetMapping("/Search")
    public String searchCustomer(
    		@ModelAttribute SearchForm searchForm, Model model
    		) {
    	
    	String name = searchForm.getName();
        String sex = searchForm.getSex();
        String birthdayFrom = searchForm.getBirthdayFrom();
        String birthdayTo = searchForm.getBirthdayTo();
        int page = 0;
        int size = 10;
    	
    	List<SearchRespone> searchResultPage = searchService.searchCustomers(name, sex, birthdayFrom, birthdayTo, page, size);

       // List<SearchRespone> searchResult = searchResultPage.getContent();

        model.addAttribute("searchResult", searchResultPage);
    	return "Search"; 
    }

    @PostMapping("/Search")
    public ResponseEntity<List<SearchRespone>> handleSearch(@ModelAttribute SearchForm searchForm, Model model) {

    	String name = searchForm.getName();
        String sex = searchForm.getSex();
        String birthdayFrom = searchForm.getBirthdayFrom();
        String birthdayTo = searchForm.getBirthdayTo();
        String sMode = searchForm.getsMode();
        
        
        int page = 0;
        
        if ("last".equals(sMode)) {
        	page = searchService.countPage(searchForm);
        }
        int size = 10;
    	
        // Call the service method with pagination parameters
        List<SearchRespone> searchResultPage = searchService.searchCustomers(name, sex, birthdayFrom, birthdayTo, page, size);

        //List<SearchRespone> searchResult = searchResultPage.getContent();
        return ResponseEntity.ok(searchResultPage);
    }
}
