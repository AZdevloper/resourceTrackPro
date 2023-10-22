package com.example.resourceTrackPro;

import java.io.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/createDatabaseAndEntities")
public class createDatabaseAndEntities extends HttpServlet {
    private String message;

    public void init() {
        message = "database entities created successfully";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("default");

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println(" <a href=\"/resourceTrackPro_war_exploded/index.jsp\">\n" +
                "                <i class='bx bx-message' ></i>\n" +
                "                <span class=\"links_name\">go home</span>\n" +
                "            </a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}