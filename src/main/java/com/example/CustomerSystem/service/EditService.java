package com.example.CustomerSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CustomerSystem.entity.MstCustomer;
import com.example.CustomerSystem.form.EditForm;
import com.example.CustomerSystem.form.EditResponse;
import com.example.CustomerSystem.repository.CustomerRepository;

@Service
public class EditService {
	@Autowired
	private CustomerRepository customerRepository;
	
	 public EditResponse getCustomerById(int customerId) {
		 MstCustomer customer = customerRepository.findByCustomerId(customerId);
		 if (customer != null) {
            return mapToEditResponse(customer);
		 }
		 return null;
	 }
	 
	 
	 private EditResponse mapToEditResponse(MstCustomer customer) {
        EditResponse editResponse = new EditResponse();
        editResponse.setCustomerId(customer.getCustomerId());
        editResponse.setCustomerName(customer.getCustomerName());
        editResponse.setSex(customer.getSex());
        editResponse.setBirthDay(customer.getBirthDay());
        editResponse.setEmail(customer.getEmail());
        editResponse.setAddress(customer.getAddress());
        return editResponse;
    }
	 
	public void addCustomer(EditForm editForm) {
		MstCustomer newCustomer = convertToMstCustomer(editForm);
		// Save the new customer
        customerRepository.save(newCustomer);
	}
	
	
	private MstCustomer convertToMstCustomer(EditForm editForm) {
        MstCustomer newCustomer = new MstCustomer();
        newCustomer.setCustomerId(editForm.getCustomerId());
        newCustomer.setCustomerName(editForm.getCustomerName());
        newCustomer.setSex(editForm.getSex());
        newCustomer.setBirthDay(editForm.getBirthDay());
        newCustomer.setEmail(editForm.getEmail());
        newCustomer.setAddress(editForm.getAddress());

        // Set other properties as needed

        return newCustomer;
    }
}
