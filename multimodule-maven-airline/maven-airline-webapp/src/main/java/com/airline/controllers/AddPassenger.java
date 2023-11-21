package com.airline.controllers;

import com.airline.models.Gender;
import com.airline.models.Passenger;
import com.airline.service.PassengerService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "AddPassenger", value = "/AddPassenger")
public class AddPassenger extends HttpServlet {

    @EJB
    PassengerService passengerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String dob_raw = request.getParameter("dob");
        String gender = request.getParameter("gender");

        Passenger passenger = new Passenger();

        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);

        String[] dobArr = dob_raw.split("\\/");

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, Integer.parseInt(dobArr[2]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dobArr[0]) -1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr[1]));

        Date dob = calendar.getTime();

        passenger.setDob(dob);

        passenger.setGender(Gender.valueOf(gender));

        System.out.println(passenger);

        passengerService.addPassenger(passenger);

        response.sendRedirect("Passengers");
    }
}