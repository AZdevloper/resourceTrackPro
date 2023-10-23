package com.example.resourceTrackPro.repositories;

import com.example.resourceTrackPro.entities.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ReservationRepositoryImpl {
    static private EntityManager em ;
    public ReservationRepositoryImpl() {

    }
    public Reservation save(Reservation  reservation) {
        System.out.println("saving reservation... " + reservation);
        em.getTransaction().begin();
        em.persist(reservation);
        em.getTransaction().commit();

        return reservation;
    }
     static{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        em = entityManagerFactory.createEntityManager();
    }
}
