package com.example.resourceTrackPro.services;

import com.example.resourceTrackPro.entities.Reservation;
import com.example.resourceTrackPro.entities.User;
import com.example.resourceTrackPro.repositories.UserRepositoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ReservationServiceTest {
    static UserRepositoryImpl userRepository;
    static  ReservationService reservationService;
    //    HttpServletRequest request;
    @BeforeAll
    public static void init() throws Exception{
        reservationService = new ReservationService();
        userRepository = mock(UserRepositoryImpl.class);

    }

    @Test
    public void test_valid_input_parameters_new_reservation_created_and_saved() {
        int userId = 1;
        int equipmentId = 1;
        Timestamp endReservationDate = new Timestamp(System.currentTimeMillis());

        Reservation  reservationResult = reservationService.add(userId, equipmentId, endReservationDate);

        assertNotNull(reservationResult);
        assertEquals(userId, reservationResult.getUser().getId());
        assertEquals(equipmentId, reservationResult.getEquipment().getId());
        assertEquals(endReservationDate, reservationResult.getEndDate());
    }
    @Test
    public void test_valid_input_parameters_returns_newly_created_reservation() {
        // Arrange

        int userId = 1;
        int equipmentId = 1;
        Timestamp endReservationDate = new Timestamp(System.currentTimeMillis());

        // Act
        Reservation result = reservationService.add(userId, equipmentId, endReservationDate);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void test_invalid_user_id_throws_illegal_argument_exception() {
        // Arrange
        ReservationService reservationService = new ReservationService();
        int userId = -1;
        int equipmentId = 1;
        Timestamp endReservationDate = new Timestamp(System.currentTimeMillis());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reservationService.add(userId, equipmentId, endReservationDate));
    }

    @Test
    public void test_valid_input_parameters_no_exceptions_thrown() {
        // Arrange
        ReservationService reservationService = new ReservationService();
        int userId = 1;
        int equipmentId = 1;
        Timestamp endReservationDate = new Timestamp(System.currentTimeMillis());

        // Act and Assert
        assertDoesNotThrow(() -> reservationService.add(userId, equipmentId, endReservationDate));
    }

}