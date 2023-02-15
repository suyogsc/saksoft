package com.saksoft.assignment.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saksoft.assignment.model.Customer;
import com.saksoft.assignment.service.CustomerService;

@RestController
public class CustomerAPIController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@RequestBody Customer customer) {
		customerService.save(customer);
		return ResponseEntity.ok("Record saved successfully.");
	}

	@GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> list() {
		return customerService.listAll();
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id) {
		try {
			Customer customer = customerService.get(id);
			System.out.println(customer.toString());
			return ResponseEntity.ok(customer);
		} catch (NoSuchElementException e) {
			return  ResponseEntity.ok("Record not found.");
		}
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<?> update(@RequestBody Customer customer, @PathVariable Integer id) {
		try {
			customerService.get(id);
			customer.setId(id);
			customerService.save(customer);
			return  ResponseEntity.ok("Record updated successfully.");
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok("Record not founded to update.");
		}
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
	    try{
	    	customerService.delete(id);
	    	}
	    catch(EmptyResultDataAccessException ex) {
	    	  return ResponseEntity.ok("Record not founded to delete.");
	    }
	    return ResponseEntity.ok("Record deleted successfully.");
	}
}
