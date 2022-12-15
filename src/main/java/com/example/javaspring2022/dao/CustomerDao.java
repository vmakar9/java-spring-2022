package com.example.javaspring2022.dao;

import com.example.javaspring2022.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

}
