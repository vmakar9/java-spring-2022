package com.example.javaspring2022.controllers;

import com.example.javaspring2022.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

     private List<Customer> customers = new ArrayList<>();

    public MainController() {
      customers.add(new Customer(1,"Vasya"));
        customers.add(new Customer(2,"Kolya"));
        customers.add(new Customer(3,"Petya"));
    }

    @GetMapping({"/customers"})
    @ResponseStatus
    public ResponseEntity<List<Customer>> getCustomers(){
        return  new ResponseEntity<>(this.customers, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<List<Customer>> addCustomer(@RequestBody Customer customer){
        System.out.println(customer);
        this.customers.add(customer);
        return new ResponseEntity<>(this.customers,HttpStatus.CREATED);
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
       System.out.println(id);
       Customer customer = this.customers.get(id-1);
       return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable int id){
       this.customers.remove(id-1);
       return new ResponseEntity<>(this.customers, HttpStatusCode.valueOf(200));
    }
    @PutMapping("/customors/{id}")
    public ResponseEntity<List<Customer>> updateCustomer(@PathVariable int id ,@RequestBody Customer customer){
        this.customers.set(id-1,customer);
        return new ResponseEntity<>(this.customers,HttpStatusCode.valueOf(200));

    }
}
