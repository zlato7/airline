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

@WebServlet(name = "AddPilot", value = "/AddPilot")
public class AddPilot extends HttpServlet {

    @EJB
    PilotService pilotService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Pilot pilot = new Pilot();

        pilot.setFirstName("Griselda");
        pilot.setLastName("Cavendish");
        pilot.setPilotRank(PilotRank.Captain);
        pilot.setPilotLicense(178245);

        pilotService.addPilot(pilot);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}