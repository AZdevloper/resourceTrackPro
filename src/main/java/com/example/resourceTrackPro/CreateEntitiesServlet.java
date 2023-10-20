package com.example.resourceTrackPro;

import java.io.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/createDatabaseAndEntities")
public class CreateEntitiesServlet extends HttpServlet {
    private String message;

    public void init() {
        EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("default");
        message = "your entities created successfully ";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}