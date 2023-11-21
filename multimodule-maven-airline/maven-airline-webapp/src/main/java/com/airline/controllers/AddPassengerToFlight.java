package com.airline.controllers;

import com.airline.service.FlightService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPassengerToFlight", value = "/AddPassengerToFlight")
public class AddPassengerToFlight extends HttpServlet
{
    @EJB
    FlightService flightService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fid = request.getParameter("fid");
        String pid = request.getParameter("pid");

        flightService.addPassengerToFlight(pid, fid);

        response.sendRedirect("Flights");
    }
}