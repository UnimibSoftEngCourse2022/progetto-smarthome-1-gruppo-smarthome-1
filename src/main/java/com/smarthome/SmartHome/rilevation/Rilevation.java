package com.smarthome.SmartHome.rilevation;
import java.sql.Timestamp;


import javax.persistence.*;

import com.smarthome.SmartHome.Device.Device;

import org.springframework.util.Assert;


@Entity
@Table(name = "rilevation")
public class Rilevation {
    

    private @GeneratedValue @Id Long id;
    
    private Double value;
    private String valueType;

    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;


    public Rilevation(){}

    public Rilevation(Timestamp timestamp, Double value, String valueType, Device device){
        this.timestamp=timestamp;
        this.value=value;
        this.valueType=valueType;
        this.device=device;
    }

    public Rilevation(Long id, Timestamp timestamp, Double value, String valueType, Device device){

        Assert.hasText(valueType, "Value type must be not null");
        Assert.notNull(device, "Device must be not null");
        
        this.id=id;
        this.timestamp=timestamp;
        this.value=value;
        this.valueType=valueType;
        this.device=device;
    }

    public Long getId(){
        return id;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
    public Double getValue(){
        return value;
    }
    public String getValueType(){
        return valueType;
    }
    public Device getDevice(){
        return device;
    }

    public void setId(Long id){
        this.id=id;
    }
    public void setTimestamp(Timestamp timestamp){
        this.timestamp=timestamp;
    }
    public void setValue(Double value){
        this.value=value;
    }
    public void setValueType(String valueType){
        this.valueType=valueType;
    }
    public void setDevice(Device device){
        this.device=device;
    }



}
