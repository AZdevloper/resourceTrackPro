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
//@Table(name = "User")
@Access(AccessType.FIELD) //
//@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")

    private String password;

   /* @Enumerated(EnumType.STRING) // Specify that the role is an Enum and store it as a String
//    @Column(name = "role")
    private Role role;*/

    public User() {

    }
    public User(String username, String email, String password, Role role){
        this.username = username;
        this.email = email;
        this.password = password;
//        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

/*    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }*/
}



