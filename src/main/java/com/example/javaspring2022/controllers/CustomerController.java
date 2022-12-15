package com.example.javaspring2022.controllers;


import com.example.javaspring2022.dao.CustomerDao;
import com.example.javaspring2022.models.Customer;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {


    private CustomerDao customerDao;



    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers(){

         List<Customer> all = customerDao.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer){
        customerDao.save(customer);
    }
}
