package com.example.javaspring2022.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ActivationToken {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   private String token = new UUID(100,999999).toString();
   private LocalDateTime expire = LocalDateTime.now(ZoneId.of("Europe/Kyiv")).plusDays(2);
}
