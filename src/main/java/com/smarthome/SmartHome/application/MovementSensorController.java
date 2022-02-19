package com.smarthome.SmartHome.application;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smarthome.SmartHome.room.RoomService;
import com.smarthome.SmartHome.user.UserService;
import com.smathome.SmartHome.Agent.Agente;
import com.smathome.SmartHome.Agent.AgenteAllarme;
import com.smathome.SmartHome.Agent.AgenteLuce;

public class MovementSensorController {
	private final RoomService roomService;
    private final UserService userService;
    private final DeviceService deviceService;
    private final RilevationService rilevationService;
    
    @Autowired
    public MovementSensorController(RoomService roomService, UserService userService, DeviceService deviceService, RilevationService rilevationService){
        this.roomService = roomService;
        this.userService = userService;
        this.deviceService = deviceService;
        this.rilevationService = rilevationService;
    }
    
    @PostMapping("/movementSensor")
    public void receiveSensorData(@RequestBody JSONObject jsonData) {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);
    	if(AgenteAllarme.getStatus()) {
    		Agente agente = new AgenteAllarme(rilevation);
    		agente.run();
    	} else {
    		Agente agente = new AgenteLuce(rilevation);
    		agente.run();
    	}
    }
    
    
}

