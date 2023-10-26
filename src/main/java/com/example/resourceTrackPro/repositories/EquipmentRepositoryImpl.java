package com.example.resourceTrackPro.repositories;

import com.example.resourceTrackPro.entities.Equipment;
import com.example.resourceTrackPro.entities.Reservation;
import com.example.resourceTrackPro.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
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

    public Optional<Equipment> findById(int equipmentId) {
//        "select u from User u where u.username = :username"
        return  em.createQuery("select e from Equipment e where e.id = :equipmentId",Equipment.class)
                .setParameter("equipmentId", equipmentId)
                .getResultStream()
                .findAny();
    }

    public List<Equipment> getAll(){

        return  em.createQuery("SELECT e FROM Equipment e", Equipment.class).getResultList();
    }

    public List<Equipment> findAllReservedEquipment(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getId();
        String jpql = "SELECT r.equipment FROM Reservation r WHERE r.user.id = :currentUserId";
        return em.createQuery(jpql, Equipment.class).setParameter("currentUserId", userId).getResultList();

    }
    static {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        em = entityManagerFactory.createEntityManager();
    }
}
