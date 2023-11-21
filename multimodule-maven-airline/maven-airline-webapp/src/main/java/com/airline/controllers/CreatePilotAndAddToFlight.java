package com.airline.controllers;

import com.airline.models.Pilot;
import com.airline.models.PilotRank;
import com.airline.service.PilotService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreatePilotAndAddToFlight", value = "/CreatePilotAndAddToFlight")
public class CreatePilotAndAddToFlight extends HttpServlet
{

    @EJB
    PilotService pilotService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Integer license = Integer.parseInt(request.getParameter("license"));
        String rank = request.getParameter("pilot_rank");
        String flightId = request.getParameter("fid");

        Pilot pilot = new Pilot();

        pilot.setFirstName(firstName);
        pilot.setLastName(lastName);
        pilot.setPilotLicense(license);
        pilot.setPilotRank(PilotRank.valueOf(rank));

        pilotService.addNewPilotToFlight(pilot, flightId);

        response.sendRedirect("Flights");
    }
}