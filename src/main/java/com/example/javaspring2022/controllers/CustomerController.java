package com.example.javaspring2022.controllers;


import com.example.javaspring2022.dao.CustomerDao;
import com.example.javaspring2022.models.Customer;
import com.example.javaspring2022.models.dto.CustomerDTO;
import com.example.javaspring2022.models.views.Views;
import com.example.javaspring2022.service.CustomerServirce;
import com.fasterxml.jackson.annotation.JsonView;
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

    private CustomerServirce customerServirce;


    @GetMapping("")
    @JsonView(Views.Client.class)
    public ResponseEntity<List<Customer>> getCustomers(){

         List<Customer> all = customerDao.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer){
//        customerDao.save(customer);
          customerServirce.save(customer);

    }
    
    @GetMapping("/{id}")
    @JsonView(Views.Admin.class)
    public ResponseEntity<Customer> getOneCustomer(@PathVariable int id){
        Customer customer = customerDao.findById(id).get();
        return  new ResponseEntity<>(customer, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable int id){
        customerDao.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO){
        Customer customer=customerDao.findById(id).get();
        customer.setName(customerDTO.getUsername());
        customerDao.save(customer);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable  String name){

//        return new ResponseEntity<>(customerDao.getByName(name),HttpStatusCode.valueOf(200));
//        return new ResponseEntity<>(customerDao.findCustomerByName(name),HttpStatusCode.valueOf(200));
//        return new ResponseEntity<>(customerServirce.customerList(name),HttpStatusCode.valueOf(200));
        return customerServirce.customerList(name);

    }
}
