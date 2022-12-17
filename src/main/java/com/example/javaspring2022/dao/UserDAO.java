package com.example.javaspring2022.dao;


import com.example.javaspring2022.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDAO extends JpaRepository<User,Integer> {

    List<User> findUserByName(String name);

    List<User> findUserBySurname(String surname);
}
