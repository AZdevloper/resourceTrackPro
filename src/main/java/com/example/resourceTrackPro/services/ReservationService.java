package com.example.resourceTrackPro.services;


import com.example.resourceTrackPro.entities.Equipment;
import com.example.resourceTrackPro.entities.Reservation;
import com.example.resourceTrackPro.entities.User;
import com.example.resourceTrackPro.repositories.EquipmentRepositoryImpl;
import com.example.resourceTrackPro.repositories.ReservationRepositoryImpl;
import com.example.resourceTrackPro.repositories.UserRepositoryImpl;

import java.sql.Timestamp;
import java.util.Optional;

public class ReservationService {
    private static final ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
    private static final EquipmentRepositoryImpl equipmentRepository = new EquipmentRepositoryImpl();
    private static final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    public ReservationService() {

    }
    public  Reservation add(String userId, String EquipmentId, Timestamp endReservationDate){
       Optional<User> user = userRepository.findById(userId);
       Optional<Equipment> equipment = equipmentRepository.findById(EquipmentId);
       if (user.isPresent() && equipment.isPresent() && endReservationDate != null) {

           Reservation reservation = new Reservation( equipment.get(),user.get(), endReservationDate);
           reservationRepository.save(reservation);
           return reservation;
       }

         else {
            // Handle the case where either the user or equipment is not found
            throw new IllegalArgumentException("User or Equipment not found");
        }


    }

}
