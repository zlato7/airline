package com.airline.controllers;

import com.airline.service.FlightService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPilotToFlight", value = "/AddPilotToFlight")
public class AddPilotToFlight extends HttpServlet
{
    @EJB
    FlightService flightService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pilotId = request.getParameter("pid");
        String flightId = request.getParameter("fid");

        flightService.addPilotToFlight(pilotId, flightId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}