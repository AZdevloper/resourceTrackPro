package com.example.resourceTrackPro.entities;


import jakarta.persistence.*;
//import lombok.NoArgsConstructor;

@Entity
//@Table(name = "Equipments")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
