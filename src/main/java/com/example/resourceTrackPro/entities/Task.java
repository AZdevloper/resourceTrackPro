package com.example.resourceTrackPro.entities;

//import ;
import jakarta.persistence.*;

//import javax.persistence.TemporalType;
import java.sql.Date;
//import java.time.temporal.Temporal;

//@Entity
@Table(name = "Tasks")
@Access(AccessType.FIELD) //
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DeadLine")
    private Date DeadLine;

    @Column(name = "priority")
    private Priority priority;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeAssigner", referencedColumnName = "id")
    private Employee employeeAssigner;

    @Column(name = "status")
    private Status status;

    public Task() {

    }

    public Task(String description, Date DeadLine, Priority priority, Employee employeeAssigner, Status status) {
        this.description = description;
        this.DeadLine = DeadLine;
        this.priority = priority;
        this.employeeAssigner = employeeAssigner;
        this.status = status;
    }

}
