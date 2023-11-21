package com.airline.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Airplane implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String planeMake;
    private String modelName;
    private Integer seatCapacity;
    @OneToOne(mappedBy = "airplaneDetail")
    private Flight flight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaneMake() {
        return planeMake;
    }

    public void setPlaneMake(String planeMake) {
        this.planeMake = planeMake;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", planeMake='" + planeMake + '\'' +
                ", modelName='" + modelName + '\'' +
                ", seatCapacity=" + seatCapacity +
                '}';
    }
}
