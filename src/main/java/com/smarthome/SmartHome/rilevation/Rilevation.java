package com.smarthome.SmartHome.rilevation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.*;

import com.smarthome.SmartHome.Device.Device;
import com.smarthome.SmartHome.Device.DeviceService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    
    public Rilevation(JSONObject jsonData, DeviceService deviceService, RilevationService rilevationService) {
    	 try {
             // da jsonData a classe rilevazione
             JSONParser parser = new JSONParser(); 
             //JSONArray ja = jsonData.getJSONArray("values"); 
             ArrayList a = (ArrayList) jsonData.get("values");
             JSONArray jsonArray = new JSONArray(a.toArray());
             
         
             JSONObject json = (JSONObject) parser.parse(jsonArray.getString(0));
             JSONObject jo = (JSONObject)json.get("data");
             Double value= Double.parseDouble(jo.get("value").toString());
             String label= jo.get("device").toString();

             Calendar calendar = Calendar.getInstance();
             java.util.Date now = calendar.getTime();
             java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
             
             Device d1=deviceService.getDeviceByLabel(label);
             
             System.out.println(jsonData);
             Rilevation r = new Rilevation(currentTimestamp, value, "double", d1);
             rilevationService.saveRilevation(r);


         } catch (JSONException | ParseException e) {
             
             e.printStackTrace();
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
