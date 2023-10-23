package com.example.resourceTrackPro.controller;

import com.example.resourceTrackPro.entities.User;
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
        // get userId from session
        User user = (User)request.getSession().getAttribute("user");
        String userId = String.valueOf(user.getId());

        String selectedEquipmentId = request.getParameter("equipmentId");
        String endReservationDateStr = request.getParameter("dateTime");
        System.out.println("get equipment "+endReservationDateStr);

        endReservationDateStr = endReservationDateStr.replace("T", " ");
        System.out.println("get equipment "+endReservationDateStr);

        Timestamp endReservationDate = Timestamp.valueOf(endReservationDateStr);
        System.out.println("after converting  "+ endReservationDateStr);
//        System.out.println(  userId + endReservationDate);

//        ReservationService  reservationService = new ReservationService();
//        reservationService.add(userId,selectedEquipmentId,endReservationDate);
        if (reservationService.add(userId,selectedEquipmentId,endReservationDate) !=null) {
            System.out.println("success");
            response.sendRedirect("view/dashboard.jsp");
        }

//        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

    }
}
