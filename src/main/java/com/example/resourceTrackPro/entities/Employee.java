package com.example.resourceTrackPro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

//@Entity
//@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;
    public Employee() {
    }

    public Employee(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
   }


}
