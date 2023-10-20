package com.example.resourceTrackPro.controller;

import com.example.resourceTrackPro.entities.User;
import com.example.resourceTrackPro.repositories.UserRepositoryImpl;
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

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        super.init();
  /*      entityManagerFactory = Persistence.createEntityManagerFactory("default");
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl(entityManagerFactory.createEntityManager());
        userService = new UserService(userRepositoryImpl);*/
        userService = new UserService();
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        /*User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);*/
        if(userService.login(username,password) ) {
            System.out.println("passed");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            response.sendRedirect("view/dashboard.jsp");
        } else {
            System.out.println("else");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("login.jsb");
        }

    }
}
