package com.example.CustomerSystem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.CustomerSystem.entity.MstCustomer;
import com.example.CustomerSystem.form.SearchForm;
import com.example.CustomerSystem.form.SearchRespone;
import com.example.CustomerSystem.repository.CustomerRepository;
import com.example.CustomerSystem.repository.CustomerSpecifications;

@Service
public class SearchService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<SearchRespone> searchCustomers(String name, String sex, String birthdayFrom, String birthdayTo, int page, int size) {
        Specification<MstCustomer> spec = CustomerSpecifications.searchCustomers(name, sex, birthdayFrom, birthdayTo);

        // Use PageRequest from org.springframework.data.domain
        Pageable pageable = PageRequest.of(page, size);
        
        Page<MstCustomer> pageCustomer = customerRepository.findAll(spec, pageable);
        if (pageCustomer.isEmpty()) {
        	return Collections.emptyList();
        }
        
        List<SearchRespone> searchResponseList  = new ArrayList<>();
        for (MstCustomer mstCustomer : pageCustomer) {
            SearchRespone searchResponse = convertToSearchResponse(mstCustomer, page);
            searchResponseList.add(searchResponse);
        }
        return searchResponseList;
    }
    
    private SearchRespone convertToSearchResponse(MstCustomer mstCustomer, int page) {
        // Logic to convert MstCustomer to SearchRespone
        // For example:
        SearchRespone searchResponse = new SearchRespone();
        searchResponse.setCustomerId(mstCustomer.getCustomerId());
        searchResponse.setCustomerName(mstCustomer.getCustomerName());
        searchResponse.setSex(mstCustomer.getSex());
        searchResponse.setBirthDay(mstCustomer.getBirthDay());
        searchResponse.setAddress(mstCustomer.getAddress());
        searchResponse.setPage(String.valueOf(page));
        // Set other properties
        return searchResponse;
    }
    
    private int countSearchCustomers(SearchForm searchForm) {
    	String name = searchForm.getName();
        String sex = searchForm.getSex();
        String birthdayFrom = searchForm.getBirthdayFrom();
        String birthdayTo = searchForm.getBirthdayTo();
        
        Specification<MstCustomer> spec = CustomerSpecifications.searchCustomers(name, sex, birthdayFrom, birthdayTo);
        return (int) customerRepository.count(spec);
    }
    
    public int countPage(SearchForm searchForm) {
    	int countPageCustomer = countSearchCustomers(searchForm);
    	int countPage = countPageCustomer / 10;
    	
    	if (countPage % 2 != 0) {
    		countPage++;
    	}
    	return countPage;
    }
}


