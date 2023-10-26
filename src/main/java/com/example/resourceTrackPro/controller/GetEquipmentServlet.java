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
    public  List<Equipment>  getReservedEquipments(HttpServletRequest request){
        return reservationService.getAllReservedEquipment(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get userId from session
        GetEquipmentServlet getEquipmentServlet = initReservation(request, response);
      /*  User user = (User)request.getSession().getAttribute("user");
        String userId = String.valueOf(user.getId());

        String selectedEquipmentId = request.getParameter("equipmentId");
        String endReservationDateStr = request.getParameter("dateTime");
        System.out.println("get equipment "+endReservationDateStr);

        endReservationDateStr = endReservationDateStr.replace("T", " ");
        System.out.println("get equipment "+endReservationDateStr);

        Timestamp endReservationDate = Timestamp.valueOf(endReservationDateStr);
        System.out.println("after converting  "+ endReservationDateStr);*/
//        System.out.println(  userId + endReservationDate);

//        ReservationService  reservationService = new ReservationService();
//        reservationService.add(userId,selectedEquipmentId,endReservationDate);
        if (reservationService.add(getEquipmentServlet.userId,getEquipmentServlet.selectedEquipmentId,getEquipmentServlet.endReservationDate) !=null) {
            List<Equipment>   reservedEquipments = getReservedEquipments(request);
            System.out.println("---->"+reservedEquipments);

            request.setAttribute("reservedEquipments", reservedEquipments);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
//            request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);
            request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);
        }

//        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

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
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));

        if (reservationService.delete(reservationId)) {
            System.out.println("success");
            response.sendRedirect("view/dashboard.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        List<Equipment> reservedEquipments = reservationService.getAllReservedEquipment(req);
        System.out.println("---->"+reservedEquipments.size());

        req.setAttribute("reservedEquipments", reservedEquipments);

        req.getRequestDispatcher("/view/dashboard.jsp").forward(req, resp);
    }

}

