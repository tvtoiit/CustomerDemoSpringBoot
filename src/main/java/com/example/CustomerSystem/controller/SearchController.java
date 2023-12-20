package com.example.CustomerSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.CustomerSystem.form.SearchRequest;
import com.example.CustomerSystem.form.SearchResponse;
import com.example.CustomerSystem.service.SearchService;

@Controller
@RequestMapping("/Search")
@Validated
public class SearchController {

    @Autowired
    private SearchService searchService;
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})

    public ModelAndView handleSearch(@ModelAttribute SearchRequest searchRequest, Model model) {	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Search");
    	SearchResponse searchResult = searchService.handleSearch(searchRequest);
    	modelAndView.addObject("searchResult", searchResult);
        return modelAndView;
    }
}
