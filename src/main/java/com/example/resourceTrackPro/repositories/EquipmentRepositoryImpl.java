package com.example.resourceTrackPro.repositories;

import com.example.resourceTrackPro.entities.Equipment;
import com.example.resourceTrackPro.entities.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class EquipmentRepositoryImpl {
    static private EntityManager em ;
    public EquipmentRepositoryImpl() {

    }

    public Equipment save(Equipment equipment) {
        em.getTransaction().begin();
        em.persist(equipment);
        em.getTransaction().commit();
        em.close();
        return equipment;
    }

    public Optional<Equipment> findById(String equipmentId) {
//        "select u from User u where u.username = :username"
        return  em.createQuery("select e from Equipment e where e.id = :equipmentId",Equipment.class)
                .setParameter("equipmentId", equipmentId)
                .getResultStream()
                .findAny();
    }

    static {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        em = entityManagerFactory.createEntityManager();
    }
}
