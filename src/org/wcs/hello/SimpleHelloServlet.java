package org.wcs.hello;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Servlet routing
@WebServlet(name = "SimpleHelloServlet", urlPatterns = {"/simple-hello"})

public class SimpleHelloServlet extends HttpServlet {

    //........................doGet
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom1 = request.getParameter("nom1");
        String prenom1 = request.getParameter("prenom1");
        String heureDuJour1 = request.getParameter("heureDuJour1");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        request.setAttribute("nom1", nom1);
        request.setAttribute("prenom1", prenom1);
        request.setAttribute("heureDuJour1", heureDuJour1);

        this.getServletContext().getRequestDispatcher( "/hello-form.jsp" ).forward( request, response );
    }


    //.......................doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get parameters from request
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String heureDuJour = request.getParameter("heureDuJour");

        //................... get time
        LocalTime time = LocalTime.parse(heureDuJour, DateTimeFormatter.ofPattern("HH:mm"));

        int hour = time.getHour();

        if (hour >= 10 && hour <= 11) {

            request.setAttribute("time", "Good morning " );

        }else if(hour >= 12 && hour <= 18){
            request.setAttribute("time", "Good afternoon " );

        }else {

            request.setAttribute("time", "Good evening " );
        }

        this.getServletContext().getRequestDispatcher( "/bienvenue.jsp" ).forward( request, response );

    }

}
