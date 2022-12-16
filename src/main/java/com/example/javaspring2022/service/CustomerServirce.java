package com.example.javaspring2022.service;


import com.example.javaspring2022.dao.CustomerDao;
import com.example.javaspring2022.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServirce {

    private CustomerDao customerDao;

    public void save(Customer customer){
        if(customer.getId()>0){
            customerDao.save(customer);
        }else {
            throw new RuntimeException("id lower zero");
        }
    }

    public ResponseEntity<List<Customer>> customerList(String name){
        if(name !=  null && !name.isBlank()){
          return  new ResponseEntity<>(customerDao.findCustomerByName(name), HttpStatusCode.valueOf(200));
        }else{
            throw new RuntimeException();
        }

    }
}
