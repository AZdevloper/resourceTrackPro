package com.example.resourceTrackPro.entities;


import jakarta.persistence.*;
//import lombok.NoArgsConstructor;

@Entity
@Table(name = "Equipments")
//@NoArgsConstructor
@Access(AccessType.FIELD)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Equipment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Equipment() {

    }
}
