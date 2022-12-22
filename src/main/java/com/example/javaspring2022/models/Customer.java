package com.example.javaspring2022.models;

import com.example.javaspring2022.models.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Admin.class})
    private int id;


    @NotEmpty
    @Length(min=3,max=13,message = "The field must be at least 5 characters")
    @JsonView({Views.Admin.class,Views.Client.class})
    private String name;

    private boolean isActivated = false;
    @JsonView({Views.Admin.class,Views.Client.class})
    private String email;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private  ActivationToken activationToken;

    public Customer(String name) {
        this.name = name;
    }

}
