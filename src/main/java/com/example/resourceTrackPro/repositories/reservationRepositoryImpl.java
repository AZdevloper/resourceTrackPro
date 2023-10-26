package com.example.resourceTrackPro.repositories;

import com.example.resourceTrackPro.entities.Reservation;
import com.example.resourceTrackPro.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    /*public List<Reservation> getFutureReservation() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
//        get all reservation where endReservationTimestamp > now
        return  em.createQuery("select r from Reservation r where r.endReservationTimestamp > :now and r.type = 'jetable' ", Reservation.class)
                .setParameter("now", now)
                .getResultStream().collect(Collectors.toList());
    }*/


    public Optional<Reservation> findById(long id) {
        return Optional.ofNullable(em.find(Reservation.class,id));
    }

    public void update(Reservation reservation) {
        em.merge(reservation);
    }


    public void delete(Reservation reservation) {
        em.remove(reservation);
    }


    public List<Reservation> findAll() {
            return em.createQuery("Select r from Reservation r", Reservation.class).getResultList();

    }



    static{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        em = entityManagerFactory.createEntityManager();
    }
    }

