package com.example.resourceTrackPro.services;


import com.example.resourceTrackPro.entities.Equipment;
import com.example.resourceTrackPro.entities.Reservation;
import com.example.resourceTrackPro.entities.User;
import com.example.resourceTrackPro.repositories.EquipmentRepositoryImpl;
import com.example.resourceTrackPro.repositories.ReservationRepositoryImpl;
import com.example.resourceTrackPro.repositories.UserRepositoryImpl;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ReservationService {
    private static final ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
    private static final EquipmentRepositoryImpl equipmentRepository = new EquipmentRepositoryImpl();
    private static final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    public ReservationService() {

    }
    public  Reservation add(int userId, int EquipmentId, Timestamp endReservationDate){
        Reservation reservation = validReservation(userId, EquipmentId, endReservationDate);
        if(reservation != null){
            reservationRepository.save(validReservation(userId, EquipmentId, endReservationDate));

        }
        return reservation;
    }

    public Reservation update(int userId, int EquipmentId, Timestamp endReservationDate){
        Reservation reservation = validReservation(userId, EquipmentId, endReservationDate);
        if(reservation != null){
            reservationRepository.update(validReservation(userId, EquipmentId, endReservationDate));

        }
        return reservation;

    }

    public Reservation validReservation(int userId, int EquipmentId, Timestamp endReservationDate) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Equipment> equipment = equipmentRepository.findById(EquipmentId);
        if (user.isPresent() && equipment.isPresent() && endReservationDate != null) {

            return new Reservation( equipment.get(),user.get(), endReservationDate);
        }

        else {
            // Handle the case where either the user or equipment is not found
            throw new IllegalArgumentException("User or Equipment not found");
        }
    }

    public boolean delete(int reservationId){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            reservationRepository.delete(reservation.get());
            return  true;
        }
        return false;
    }
    public List<Reservation> getReservationList(){
      return   reservationRepository.findAll();

    }
    public List<Reservation> getAllReservedEquipment(HttpServletRequest request){
        return   equipmentRepository.findAllReservedEquipment(request);

    }

}
