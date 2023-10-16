package com.example.resourceTrackPro.entities;

//import ;
import com.example.resourceTrackPro.Other.Role;
import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

//import javax.management.relation.Role;
import java.util.Set;

@Entity
@Table(name = "User")
@Access(AccessType.FIELD) //
//@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")

    private String password;

    @Enumerated(EnumType.STRING) // Specify that the role is an Enum and store it as a String
//    @Column(name = "role")
    private Role role;

    public User() {

    }
    public User(String name, String email, String password, Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    }



