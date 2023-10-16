package com.example.resourceTrackPro.entities;

//import ;
import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

import java.util.Set;

//@Entity
@Table(name = "Deppartments")
@Access(AccessType.FIELD) //
//@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
//    @Getter
//    @Setter
    private String name;

    @Column(name = "description")
//    @Getter
//    @Setter
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mangerId", referencedColumnName = "id")
    private Employee manager;

    public Department(String name, String description){
        this.name = name;
        this.description = description;
    }

    @OneToMany(mappedBy="Department")
    private Set<Employee> employeesList;


    public Department() {

    }
}
