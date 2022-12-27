package com.example.javaspring2022.dao;

import com.example.javaspring2022.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {


     Customer findCustomerByLogin(String login);
}
