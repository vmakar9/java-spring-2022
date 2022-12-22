package com.example.javaspring2022.service;


import com.example.javaspring2022.dao.CustomerDao;
import com.example.javaspring2022.models.ActivationToken;
import com.example.javaspring2022.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServirce {

    private CustomerDao customerDao;

    private MailService mailService;
    public void save(Customer customer){
        customer.setActivationToken(new ActivationToken());
        customerDao.save(customer);
        //todo send email
        mailService.send(customer);
        System.out.println(customer.getActivationToken());
    }

    public ResponseEntity<List<Customer>> customerList(String name){
        if(name !=  null && !name.isBlank()){
          return  new ResponseEntity<>(customerDao.findCustomerByName(name), HttpStatusCode.valueOf(200));
        }else{
            throw new RuntimeException();
        }

    }

    public Customer getCustomerById(int id){
       return customerDao.findById(id).get();
    }

    public void updateCustomer(Customer customer){
        customerDao.save(customer);
    }

    public  Customer byToken(String token){
        return customerDao.byToken(token);
    }

    public void activate(Customer customer){
        if(customer.getActivationToken().getExpire().isAfter(LocalDateTime.now()) ) {
            customer.setActivated(true);
            updateCustomer(customer);
        }

    }
}
