package com.example.CustomerSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.CustomerSystem.entity.MstCustomer;
import com.example.CustomerSystem.form.SearchRequest;
import com.example.CustomerSystem.form.SearchResponse;
import com.example.CustomerSystem.repository.CustomerRepository;
import com.example.CustomerSystem.repository.CustomerSpecifications;

@Service
public class SearchService {

    @Autowired
    private CustomerRepository customerRepository;

    public SearchResponse searchCustomers(SearchRequest searchRequest, int page, int size) {
        Specification<MstCustomer> spec = CustomerSpecifications.searchCustomers(searchRequest);

        // Use PageRequest from org.springframework.data.domain
        Pageable pageable = PageRequest.of(page, size);
        
        Page<MstCustomer> pageCustomer = customerRepository.findAll(spec, pageable);
        
        SearchResponse responseSearch = new SearchResponse();
        if (!pageCustomer.isEmpty()) {
        	responseSearch.setDataSearch(pageCustomer.getContent());
        }
 
        return responseSearch;
    }
    
    
    private int countSearchCustomers(SearchRequest searchRequest) {
        Specification<MstCustomer> spec = CustomerSpecifications.searchCustomers(searchRequest);
        return (int) customerRepository.count(spec);
    }
    
    private int countPage(SearchRequest searchForm) {
    	int countPageCustomer = countSearchCustomers(searchForm);
    	int countPage = countPageCustomer / 10;
    	
    	if (countPage % 2 != 0) {
    		countPage++;
    	}
    	return countPage;
    }
    
    public SearchResponse handleSearch(SearchRequest searchRequest) {
    	int page = 0;
    	int size = 15;
    	SearchResponse searchResponse = searchCustomers(searchRequest, page, size);
    	
    	
    	return searchResponse;
    }
}


