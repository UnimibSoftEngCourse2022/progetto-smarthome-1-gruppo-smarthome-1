package com.smarthome.SmartHome.application;

import com.smarthome.SmartHome.Device.*;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationRepository;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smarthome.SmartHome.room.Room;
import com.smarthome.SmartHome.room.RoomService;
import com.smarthome.SmartHome.user.User;
import com.smarthome.SmartHome.user.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping()
public class ApplicationController {
    private final RoomService roomService;
    private final UserService userService;
    private final DeviceService deviceService;
    private final RilevationService rilevationService;

    @Autowired
    public ApplicationController(RoomService roomService, UserService userService, DeviceService deviceService, RilevationService rilevationService){
        this.roomService = roomService;
        this.userService = userService;
        this.deviceService = deviceService;
        this.rilevationService = rilevationService;
    }

    @GetMapping
    public String index(){
        List<User> users = userService.getUsers();
        List<Room> rooms = roomService.getRooms();

        String result = "";
        result += "<h1>SmartHome Application</h1>";
        result += "<h2>Users</h2>" + users.toString();

        result += "<h2>Rooms</h2>";

        for (Room room: rooms) {
            result += "<h3>" + room.getName() + "</h3>";
            List<Device> devices = deviceService.getDeviceByRoom(room);

            for (Device device: devices) {
                result += "<ul>" + device.getLabel() + "</ul>";
            }
        }


        return result;
    }

    @PostMapping(path = "/api/v1/sensor")
    public void reciveSensorData(@RequestBody JSONObject jsonData) {
        //try {
            // da jsonData a classe rilevazione
            JSONParser parser = new JSONParser(); 
            //JSONArray ja = jsonData.getJSONArray("values"); 
            /*ArrayList a = (ArrayList) jsonData.get("values");
            JSONArray jsonArray = new JSONArray(a.toArray());
            
        
            JSONObject json = (JSONObject) parser.parse(jsonArray.getString(0));
            JSONObject jo = (JSONObject)json.get("data");
            Double value= Double.parseDouble(jo.get("value").toString());

            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            */
            
            System.out.println(jsonData);
            //Rilevation r = new Rilevation(currentTimestamp, value, "double", device);


        //} catch (JSONException | ParseException e) {
            
        //    e.printStackTrace();
        //}  
        
        
    }


    @GetMapping(path = "api/v1/sensor/{label}/data")
    public String getSensorData(@PathVariable("label") String label){

        Sensor s = (Sensor) deviceService.getDeviceByLabel(label);
        if (s != null) {
            return String.valueOf(s.getDataFeed());
        }
        else{
            return "";
        }
    }

    @GetMapping(path = "api/v1/actuator/{label}/state")
    public String getActuatorCurrentState(@PathVariable("label") String label){

        Actuator a = (Actuator) deviceService.getDeviceByLabel(label);
        if (a != null) {
            return a.getCurrentState();
        }
        else{
            return "";
        }
    }

    @PostMapping(path = "api/v1/actuator/{label}/signal")
    public String actuatorControlSignal(@PathVariable("label") String label){

        Actuator a = (Actuator) deviceService.getDeviceByLabel(label);
        if (a != null) {
            a.controlSignal();
            return "Success";
        }
        else{
            return "";
        }
    }

    @GetMapping(path = "api/v1/test")
    public String testMethod() {
        /*Room testRoom = new Room("test");
        Actuator testActuator = new Actuator("tapparella", Category.LUCE,testRoom);

        testActuator.controlSignal();

        return testActuator.getCurrentState();*/

        Room testRoom = new Room("test");
        Sensor testSensor = new Sensor("thermometer1", Category.TEMPERATURA, testRoom);

        double result = testSensor.getDataFeed();

        return String.valueOf(result);

    }

}
