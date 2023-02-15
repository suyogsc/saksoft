package com.saksoft.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saksoft.assignment.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
