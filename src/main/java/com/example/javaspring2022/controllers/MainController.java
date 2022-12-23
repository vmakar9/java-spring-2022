package com.example.javaspring2022.controllers;

import com.example.javaspring2022.dao.CustomerDAO;
import com.example.javaspring2022.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {

    private CustomerDAO customerDAO;

    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String open(){
        return "open";
    }

    @PostMapping("/save")
    public void save(@RequestBody Customer customer){
     String password= customer.getPassword();
     String encode = passwordEncoder.encode(password);
     customer.setPassword(encode);
      customerDAO.save(customer);
    }

    @GetMapping("/secure")
    public String secure(){
        return "secure";
    }
}
