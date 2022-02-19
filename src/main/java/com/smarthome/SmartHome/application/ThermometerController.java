package com.smarthome.SmartHome.application;

import org.springframework.beans.factory.annotation.Autowired;

import com.smarthome.SmartHome.Device.*;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationRepository;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smarthome.SmartHome.room.Room;
import com.smarthome.SmartHome.room.RoomService;
import com.smarthome.SmartHome.user.User;
import com.smarthome.SmartHome.user.UserService;
import com.smathome.SmartHome.Agent.Agente;
import com.smathome.SmartHome.Agent.AgenteTemperatura;

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
public class ThermometerController {

	private final RoomService roomService;
    private final UserService userService;
    private final DeviceService deviceService;
    private final RilevationService rilevationService;

    @Autowired
    public ThermometerController(RoomService roomService, UserService userService, DeviceService deviceService, RilevationService rilevationService){
        this.roomService = roomService;
        this.userService = userService;
        this.deviceService = deviceService;
        this.rilevationService = rilevationService;
    }
    
    @PostMapping("/thermometer")
    public void receiveSensorData(@RequestBody JSONObject jsonData) {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);
    	if(AgenteTemperatura.getStatus()) {
    		Agente agente = new AgenteTemperatura(rilevation);
    		agente.run();
    	}
    }
    
    
}
