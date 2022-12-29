package com.example.javaspring2022.configs.dao;

import com.example.javaspring2022.wsmodels.CustomMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomMessageDAO extends MongoRepository<CustomMessage,String> {

}
