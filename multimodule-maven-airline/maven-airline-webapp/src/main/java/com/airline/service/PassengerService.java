package com.airline.service;

import com.airline.models.Flight;
import com.airline.models.Passenger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@LocalBean
public class PassengerService
{
    public PassengerService()
    {
    }
    @PersistenceContext(unitName = "airline")
    private EntityManager em;

    public Passenger addPassenger(Passenger passenger){

        em.persist(passenger);

        return passenger;
    }

    public List<Passenger> getPassengers(){

        TypedQuery<Passenger> query = em.createQuery("SELECT p FROM Passenger p", Passenger.class);

        List<Passenger> pList = query.getResultList();

        pList.forEach(x->{
            x.getFlights().size();
        });

        return pList;
    }

    public void addFlightTicketToPassenger(String flightId, String passengerId){

        // Getting the passenger by id

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);

        Root<Passenger> pRoot = cqPassenger.from(Passenger.class);

        cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));

        TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);

        Passenger p = pQuery.getSingleResult();

        // Getting the flight by id

        builder = em.getCriteriaBuilder();

        CriteriaQuery<Flight> cqFlight = builder.createQuery(Flight.class);

        Root<Flight> fRoot = cqFlight.from(Flight.class);

        cqFlight.select(fRoot).where(builder.equal(fRoot.get("id").as(Integer.class), flightId));

        TypedQuery<Flight> fQuery = em.createQuery(cqFlight);

        Flight f = fQuery.getSingleResult();

        // Associate the flight with the passenger

        List<Flight> fList = p.getFlights();

        fList.add(f);

        p.setFlights(fList);

        f.getPassengers().add(p);
    }

    public Passenger getPassenger(Integer passengerId){

        // Getting the passenger by id

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);

        Root<Passenger> pRoot = cqPassenger.from(Passenger.class);

        cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));

        TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);

        Passenger p = null;

        try
        {
            p = pQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        p.getFlights().size();

        return p;
    }

    public Passenger updatePassenger(Integer passengerId, Passenger pUpdated){

        Passenger p = em.find(Passenger.class, passengerId);

        if (p == null){
            return  null;
        }
        if (pUpdated.getFirstName() != null){
            p.setFirstName(pUpdated.getFirstName());
        }
        if (pUpdated.getLastName() != null){
            p.setLastName(pUpdated.getLastName());
        }
        if (pUpdated.getDob() != null){
            p.setDob(pUpdated.getDob());
        }
        if (pUpdated.getGender() != null){
            p.setGender(pUpdated.getGender());
        }

        p.getFlights().size();

        return p;
    }

    public Passenger updatePassenger2(Integer passengerId, Passenger pUpdated)
    {
        pUpdated.setId(passengerId);

        Passenger pCheckExist = em.find(Passenger.class, passengerId);

        if (pCheckExist == null){
            return  null;
        }

        em.merge(pUpdated);
        
        return pUpdated;
    }
}
