package com.example.javaspring2022.controllers;

import com.example.javaspring2022.models.User;
import com.example.javaspring2022.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable int id,@RequestBody User user){
        userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){userService.deleteUser(id);}

    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<User>> getUserBySurName(@PathVariable String surname){
        return userService.getUserBySurName(surname);
    }

}
