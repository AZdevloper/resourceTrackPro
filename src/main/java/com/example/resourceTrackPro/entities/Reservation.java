package com.example.resourceTrackPro.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;
//import javax.persistence.Entity;

@Entity
@Table(name = "reservations")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "equipmentId")  // Define the foreign key column name
    private Equipment equipment;

    private Timestamp reservationTimestamp;
    private Timestamp endTimestamp;

    public Reservation( Timestamp endTimestamp) {
        this.reservationTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime() );
        this.endTimestamp = endTimestamp;
    }

    public Timestamp getEndDate() {
        return endTimestamp;
    }

    public void setEndDate(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Timestamp getReservationTimestamp() {
        return reservationTimestamp;
    }
    public void setReservationTimestamp(Timestamp reservationTimestamp) {
        this.reservationTimestamp = reservationTimestamp;
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