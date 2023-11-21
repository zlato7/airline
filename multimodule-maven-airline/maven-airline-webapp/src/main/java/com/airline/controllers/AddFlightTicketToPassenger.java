package com.airline.controllers;

import com.airline.service.PassengerService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddFlightTicketToPassenger", value = "/AddFlightTicketToPassenger")
public class AddFlightTicketToPassenger extends HttpServlet
{
    @EJB
    PassengerService passengerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fid = request.getParameter("fid");
        String pid = request.getParameter("pid");

        passengerService.addFlightTicketToPassenger(fid, pid);

        response.sendRedirect("Passengers");
    }
}