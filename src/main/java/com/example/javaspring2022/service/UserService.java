package com.example.javaspring2022.service;


import com.example.javaspring2022.dao.CustomerDao;
import com.example.javaspring2022.dao.UserDAO;
import com.example.javaspring2022.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private UserDAO userDAO;
    private final CustomerDao customerDao;

    ;

    public void save(User user){
        if(user.getId()>0){
            userDAO.save(user);
        }else {
            throw new RuntimeException("id lower that zero");
        }
    }

    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = userDAO.findAll();
        return new ResponseEntity<>(userList, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<User> getUserById(int id){
       User user = userDAO.findById(id).get();
       return new ResponseEntity<>(user,HttpStatusCode.valueOf(200));
    }

    public void updateUser(int id){
        User user = userDAO.findById(id).get();
        user.setName(user.getName());
        user.setSurname(user.getSurname());
        user.setEmail(user.getEmail());
    }

    public void deleteUser(int id){
        customerDao.deleteById(id);
    }

    public ResponseEntity<List<User>> getUserByName(String name){
        if(name !=  null && !name.isBlank()){
            return new ResponseEntity<>(userDAO.findUserByName(name),HttpStatusCode.valueOf(200));
        }else {
            throw new RuntimeException();
        }
     }

    public ResponseEntity<List<User>> getUserBySurName(String surname){
        if(surname !=  null && !surname.isBlank()){
            return new ResponseEntity<>(userDAO.findUserBySurname(surname),HttpStatusCode.valueOf(200));
        }else {
            throw new RuntimeException();
        }
    }
}
