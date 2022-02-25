package com.smarthome.smarthome.rilevation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.*;

import com.smarthome.smarthome.device.Actuator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smarthome.smarthome.device.Device;
import com.smarthome.smarthome.device.DeviceService;

@Entity
@Table(name = "rilevation")
public class Rilevation
{
    private @GeneratedValue @Id Long id;
    private Double value;
    private String valueType;
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "device_id")
    @JsonIgnore
    public Device device;


    public Rilevation(){}

    public Rilevation(Timestamp timestamp, Double value, String valueType, Device device)
    {
        this.timestamp=timestamp;
        this.value=value;
        this.valueType=valueType;
        this.device=device;
    }

    public Rilevation(Long id, Timestamp timestamp, Double value, String valueType, Device device)
    {
        Assert.hasText(valueType, "Value type must be not null");
        Assert.notNull(device, "Device must be not null");
        
        this.id=id;
        this.timestamp=timestamp;
        this.value=value;
        this.valueType=valueType;
        this.device=device;
    }
    
    public Rilevation(JSONObject jsonData, DeviceService deviceService, RilevationService rilevationService)
    {
    	 try
         {
             // da jsonData a classe rilevazione
             JSONParser parser = new JSONParser(); 
             ArrayList a = (ArrayList) jsonData.get("values");
             JSONArray jsonArray = new JSONArray(a.toArray());

             JSONObject json = (JSONObject) parser.parse(jsonArray.getString(0));
             JSONObject jo = (JSONObject)json.get("data");
             Double val= Double.parseDouble(jo.get("value").toString());
             String label= jo.get("device").toString();

             Calendar calendar = Calendar.getInstance();
             java.util.Date now = calendar.getTime();
             java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
             
             Device d1=deviceService.getDeviceByLabel(label);

             Rilevation r = new Rilevation(currentTimestamp, val, "double", d1);
             rilevationService.saveRilevation(r);
         }
         catch (JSONException | ParseException e)
         {
             Logger logger = Logger.getLogger(Actuator.class.getName());

             logger.log(Level.INFO, e.getMessage());
         }
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

    public String toString(){
        org.json.JSONObject jo= new org.json.JSONObject();
        jo.put("rilevation", new org.json.JSONObject()
            .put("id", id)
            .put("timestamp", timestamp.toString())
            .put("value", value)
            .put("valueType", valueType)
        );

        return jo.toString();
    }
}
