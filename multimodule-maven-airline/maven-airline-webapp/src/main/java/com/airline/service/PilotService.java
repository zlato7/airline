package com.airline.service;

import com.airline.models.Flight;
import com.airline.models.Pilot;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class PilotService {

    @PersistenceContext(unitName = "airline")
    EntityManager em;
    public void addPilot(Pilot pilot){

        em.persist(pilot);
    }

    public void addNewPilotToFlight(Pilot p, String flightId){

        em.persist(p);

        TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);

        fQuery.setParameter("id", Integer.parseInt(flightId));

        Flight f = fQuery.getSingleResult();

        List<Pilot> pList = f.getPilots();

        pList.add(p);

        f.setPilots(pList);

        p.setFlightForPilot(f);
    }
}
