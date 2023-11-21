package com.airline.webservices.rest;

import com.airline.models.Passenger;
import com.airline.service.PassengerService;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/passengers")
public class PassengersWebService {
    @PersistenceContext(unitName = "airline")
    EntityManager em;
    @EJB
    PassengerService passengerService;

    @Context
    UriInfo pUriInfo;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Passenger> getPassengers(){

        List<Passenger> pList = passengerService.getPassengers();

        return pList;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{passenger_Id}")
    public Passenger getPassenger(@PathParam("passenger_Id") Integer passengerId){

        Passenger p = passengerService.getPassenger(passengerId);

        if (p == null){
            throw new NotFoundException("The passenger with an id of " + passengerId + " was not found");
        }

        return p;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPassenger(Passenger p){

       p = passengerService.addPassenger(p);

        UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();

        URI pUri = pUriBuilder.path(String.valueOf(p.getId())).build();

        return Response.created(pUri).build();
    }
    @PUT
    @Path("/edit/{pid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public  Response updatePassenger(@PathParam("pid") Integer passengerId, Passenger pUpdated){

        pUpdated = passengerService.updatePassenger(passengerId, pUpdated);

        if (pUpdated == null){
            throw new NotFoundException("The passenger with an id of " + passengerId + " was not found.");
        }
        return Response.ok(pUpdated).build();
    }

    @PUT
    @Path("/edit2/{pid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public  Response updatePassenger2(@PathParam("pid") Integer passengerId, Passenger pUpdated){

        pUpdated = passengerService.updatePassenger2(passengerId, pUpdated);

        if (pUpdated == null){
            throw new NotFoundException("The passenger with an id of " + passengerId + " was not found.");
        }
        return Response.ok(pUpdated).build();
    }
}
