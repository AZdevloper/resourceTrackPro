package com.example.resourceTrackPro.entities;

//import ;
import jakarta.persistence.*;

import java.sql.Date;

//@Entity
//@Table(name = "Task")
@Access(AccessType.FIELD) //
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "DeadLine")
    private Date DeadLine;

    @Column(name = "priority")
    private Priority priority;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeAssigner", referencedColumnName = "id")
    private Employee employeeAssigner;

    @Column(name = "status")
    private Status status;

}
