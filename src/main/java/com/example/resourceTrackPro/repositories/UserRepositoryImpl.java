package com.example.resourceTrackPro.repositories;
//import jakarta.inject.Inject;
import com.example.resourceTrackPro.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class UserRepositoryImpl implements  UserRepositoryInterface {
    static private EntityManager em ;
    public UserRepositoryImpl( EntityManager em) {
        this.em = em;
    }
    public UserRepositoryImpl() {

    }
    @Override
    public User save(User user) {
        System.out.println("saving user... " + user);
        /*  Role userRole = em.createQuery("select r from Role r where r.name = :name", Role.class)
                .setParameter("name", "USER")
                .getResultStream().findAny().orElse(null);
        if (userRole == null) {
            userRole = new Role("USER");
            em.persist(userRole);
        }*/
        //user.setRole(userRole);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }
    public Optional<User> findByUsername(String username) {
        System.out.println("Getting user by username: " + "abdelaziz");
       /* System.out.println("return in entity manager " + em.createQuery("select u from User u where u.id = :username", User.class)
                .setParameter("username", 1)
                .getResultStream().findAny());*/

         return em.createQuery("select u from User u where u.username = :username", User.class)
                 .setParameter("username", username)
                 .getResultStream().findAny();
    }
    public Optional<User> findById(String userId) {
        return em.createQuery("select u from User u where u.id = :userId", User.class)
                .setParameter("userId", userId)
                .getResultStream().findAny();
    }

    static {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        em = entityManagerFactory.createEntityManager();
    }
}
