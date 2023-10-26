package com.example.resourceTrackPro.controller;


import com.example.resourceTrackPro.services.ReservationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GetEquipment", value = "/GetEquipment")
public class RemoveReservationServlet extends HttpServlet {
    private ReservationService  reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        reservationService = new ReservationService();

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doDelete(request, response);
        int selectedEquipmentId = Integer.parseInt( request.getParameter("equipmentId"));
        reservationService.delete(selectedEquipmentId);

        request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);

    }
}
