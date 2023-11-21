package com.airline.webservices.rest;

import com.airline.models.Flight;
import com.airline.service.FlightService;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/flights")
@Transactional
public class FlightsWebService {
    @PersistenceContext(unitName = "airline")
    EntityManager em;
    @EJB
    FlightService flightService;

    @Context
    UriInfo fUriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlights(){

        List<Flight> fList = flightService.getFlights();

        return fList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{flight_id}")
    public Flight getFlight(@PathParam("flight_id") Integer flightId){

        Flight f = flightService.getFlight(flightId);

        if (f == null){
            throw new NotFoundException("The flight with an id of " + flightId + " was not found");
        }

        return f;
    }

    @DELETE
    @Path("{flight_id}")
    public Response deleteFlight(@PathParam("flight_id") Integer flightId){

        Flight flightToRemove = em.find(Flight.class, flightId);

        if (flightToRemove == null){
            throw new NotFoundException("The flight with an id of " + flightId + "was not found.");
        }

        em.remove(flightToRemove);

        return  Response.noContent().build();
    }
}
