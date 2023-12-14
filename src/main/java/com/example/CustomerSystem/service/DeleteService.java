package com.example.CustomerSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CustomerSystem.repository.CustomerRepository;

@Service
public class DeleteService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public void softDeleteCustomers(String[] selecValue) {
        List<Integer> customerIds = new ArrayList<>();
        
        for (String value : selecValue) {
            String[] ids = value.split(",");
            for (String id : ids) {
                customerIds.add(Integer.parseInt(id.trim()));
            }
        }

        customerRepository.softDeleteByIds(customerIds);
    }
	 
}
