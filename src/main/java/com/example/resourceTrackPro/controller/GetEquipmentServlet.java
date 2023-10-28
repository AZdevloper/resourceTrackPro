package com.example.resourceTrackPro.controller;

import com.example.resourceTrackPro.entities.Equipment;
import com.example.resourceTrackPro.entities.Reservation;
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
import java.util.List;

@WebServlet(name = "GetEquipment", value = "/GetEquipment")
public class GetEquipmentServlet extends HttpServlet {

    int userId;
    int selectedEquipmentId;
    Timestamp endReservationDate;
    private ReservationService      reservationService;

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        reservationService = new ReservationService();

    }
    public GetEquipmentServlet(){}

    public GetEquipmentServlet(int userId, int selectedEquipmentId, Timestamp endReservationDate) {
        this.endReservationDate = endReservationDate;
        this.userId = userId;
        this.selectedEquipmentId = selectedEquipmentId;
    }

    public GetEquipmentServlet(ReservationService reservationService) {

        this.reservationService = reservationService;
    }
//    public  List<Reservation>  getReservedEquipments(HttpServletRequest request){
//        return reservationService.getAllReservedEquipment(request);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method != null && method.equals("DELETE")) {

            System.out.println("Delete her own inventory from database " );
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));

            if (reservationService.delete(reservationId)) {
                System.out.println("success");
                response.sendRedirect("view/dashboard.jsp");
            }
        } else {

            GetEquipmentServlet getEquipmentServlet = initReservation(request, response);

            if (reservationService.add(getEquipmentServlet.userId,getEquipmentServlet.selectedEquipmentId,getEquipmentServlet.endReservationDate) !=null) {
                System.out.println("passed");

                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                response.sendRedirect("view/dashboard.jsp");
            }
        }



    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);
        initReservation(request, response);

        if (reservationService.update(userId,selectedEquipmentId,endReservationDate) !=null) {
            System.out.println("success");
            response.sendRedirect("view/dashboard.jsp");
        }
    }

    public GetEquipmentServlet initReservation(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getId();

        int selectedEquipmentId = Integer.parseInt( request.getParameter("equipmentId"));
        String endReservationDateStr = request.getParameter("dateTime");
        System.out.println("get equipment "+endReservationDateStr);

        endReservationDateStr = endReservationDateStr.replace("T", " ");
        System.out.println("get equipment "+endReservationDateStr);

        Timestamp endReservationDate = Timestamp.valueOf(endReservationDateStr);
        System.out.println("after converting  "+ endReservationDateStr);

       return  new GetEquipmentServlet(userId,selectedEquipmentId, endReservationDate);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doDelete(request, response);
        System.out.println("Delete her own inventory from database " );
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));

        if (reservationService.delete(reservationId)) {
            System.out.println("success");
            response.sendRedirect("view/dashboard.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

//        List<Reservation> reservedEquipments = reservationService.getAllReservedEquipment(req);
//        System.out.println("---->"+reservedEquipments.size());
//
//        req.setAttribute("reservedEquipments", reservedEquipments);

        req.getRequestDispatcher("/view/dashboard.jsp").forward(req, resp);
    }

}

