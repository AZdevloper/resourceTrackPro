package com.example.resourceTrackPro.controller;

import com.example.resourceTrackPro.services.ReservationService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "GetEquipment", value = "/GetEquipment")
public class GetEquipmentServlet extends HttpServlet {

    private ReservationService      reservationService;

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        super.init();
  /*      entityManagerFactory = Persistence.createEntityManagerFactory("default");
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl(entityManagerFactory.createEntityManager());
        userService = new UserService(userRepositoryImpl);*/

        reservationService = new ReservationService();

    }

    public GetEquipmentServlet() {

    }

    public GetEquipmentServlet(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String selectedEquipmentId = request.getParameter("selectedEquipment");
        String endReservationDateStr = request.getParameter("endReservationDate");

        Timestamp endReservationDate = Timestamp.valueOf(endReservationDateStr);

        System.out.println(selectedEquipmentId + userId +endReservationDate);

        ReservationService  reservationService = new ReservationService();
        reservationService.add(userId,selectedEquipmentId,endReservationDate);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

    }
}
