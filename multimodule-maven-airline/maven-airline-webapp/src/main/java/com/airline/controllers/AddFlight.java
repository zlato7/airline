package com.airline.controllers;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.FlightDestinations;
import com.airline.service.FlightService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "AddFlight", value = "/AddFlight")
public class AddFlight extends HttpServlet {

    @EJB
    private FlightService flightService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Flight flight = new Flight();

        String from_destination = request.getParameter("from_destination");

        flight.setFlightOrigin(FlightDestinations.valueOf(from_destination));

        String to_destination = request.getParameter("to_destination");

        flight.setFlightDestination(FlightDestinations.valueOf(to_destination));

        String price = request.getParameter("price");

        flight.setPrice(Integer.parseInt(price));

        Integer year = Integer.parseInt(request.getParameter("year"));
        Integer month = Integer.parseInt(request.getParameter("month"));
        Integer day = Integer.parseInt(request.getParameter("day"));
        Integer hour = Integer.parseInt(request.getParameter("hour"));
        Integer minute = Integer.parseInt(request.getParameter("minute"));

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Date flightTime = calendar.getTime();

        System.out.println(flightTime);

        flight.setFlightTime(flightTime);

        Airplane airplane = new Airplane();

        String planeMake = request.getParameter("airplane_make");
        String planeModelName = request.getParameter("airplane_model");
        Integer seating = Integer.parseInt(request.getParameter("airplane_seating"));

        airplane.setModelName(planeModelName);
        airplane.setPlaneMake(planeMake);
        airplane.setSeatCapacity(seating);

        flight.setAirplaneDetail(airplane);

        System.out.println(flight);
        System.out.println(airplane);

        flightService.addFlight(flight, airplane);

        response.sendRedirect("Flights");
    }
}