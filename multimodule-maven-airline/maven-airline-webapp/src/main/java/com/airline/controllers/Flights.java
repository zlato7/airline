package com.airline.controllers;

import com.airline.models.Flight;
import com.airline.service.FlightService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Flights", value = "/Flights")
public class Flights extends HttpServlet
{
    @EJB
    FlightService flightService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Flight> fList = (List<Flight>) flightService.getFlights();

        request.setAttribute("flight_list", fList);

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/flights_list.jsp");

        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}