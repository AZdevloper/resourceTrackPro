package com.example.resourceTrackPro.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;
//import javax.persistence.Entity;

@Entity
//@Table(name = "reservations")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
//    @JoinColumn(name = "equipmentId")  // Define the foreign key column name
    private Equipment equipment;

    private Timestamp startReservationTimestamp;
    private Timestamp endReservationTimestamp;

    public  Reservation(Equipment equipment,User  user, Timestamp endReservationTimestamp) {
        this.equipment = equipment;
        this.user = user;
        this.startReservationTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime() );
        this.endReservationTimestamp = endReservationTimestamp;
    }

    public Reservation( Timestamp endReservationTimestamp) {
        this.startReservationTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime() );
        this.endReservationTimestamp = endReservationTimestamp;
    }

    public Timestamp getEndDate() {
        return endReservationTimestamp;
    }

    public void setEndDate(Timestamp endReservationTimestamp) {
        this.endReservationTimestamp = endReservationTimestamp;
    }

    public Timestamp getstartReservationTimestamp() {
        return startReservationTimestamp;
    }
    public void setstartReservationTimestamp(Timestamp startReservationTimestamp) {
        this.startReservationTimestamp = startReservationTimestamp;
    }
    public Equipment getEquipment() {
        return equipment;
    }
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Reservation() {

    }

    // Constructors, getters, and setters
}