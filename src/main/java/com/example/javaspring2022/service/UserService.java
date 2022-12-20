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
 ;

    public void saveUser(User user){
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

    public User getUserById(int id){
      return userDAO.findById(id).get();

    }

    public void updateUserById(User user){
       userDAO.save(user);
    }

    public void deleteUser(int id){
        userDAO.deleteById(id);
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
