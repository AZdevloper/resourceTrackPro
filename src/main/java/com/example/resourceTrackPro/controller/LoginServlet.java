package com.example.resourceTrackPro.controller;

import com.example.resourceTrackPro.entities.Equipment;
import com.example.resourceTrackPro.entities.User;
import com.example.resourceTrackPro.repositories.UserRepositoryImpl;
import com.example.resourceTrackPro.services.ReservationService;
import com.example.resourceTrackPro.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private ReservationService reservationService;

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        super.init();
  /*      entityManagerFactory = Persistence.createEntityManagerFactory("default");
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl(entityManagerFactory.createEntityManager());
        userService = new UserService(userRepositoryImpl);*/
        userService = new UserService();
        reservationService = new ReservationService();

    }

    public LoginServlet() {

    }

    public LoginServlet(UserService  userService) {
        this.userService = userService;
    }


    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        RequestDispatcher dispatcher = request.getRequestDispatcher("singup.jsp");
        dispatcher.forward(request, response);

    }

    public  List<Equipment>  getReservedEquipments(HttpServletRequest request){
        return reservationService.getAllReservedEquipment(request);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(userService.login(username,password,request) ) {

            session.setAttribute("name","joly");
            List<Equipment>   reservedEquipments = getReservedEquipments(request);
            System.out.println("---->"+reservedEquipments);

            request.setAttribute("reservedEquipments", reservedEquipments);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
//            request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);
            request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);

//            response.sendRedirect("view/dashboard.jsp");
        } else {
            System.out.println("failled to signin");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("view/404.jsp");
        }

    }
}
