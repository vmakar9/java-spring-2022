package com.example.javaspring2022.controllers;

import com.example.javaspring2022.dao.UserDAO;
import com.example.javaspring2022.models.User;
import com.example.javaspring2022.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private final UserDAO userDAO;

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
       User user = userDAO.findById(id).get();
       return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable int id,@RequestBody User user){
    User user1 = userService.getUserById(id);

       user1.setName(user.getName());
       user1.setSurname(user.getSurname());
       user1.setEmail(user.getEmail());
       userService.updateUserById(user1);
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
