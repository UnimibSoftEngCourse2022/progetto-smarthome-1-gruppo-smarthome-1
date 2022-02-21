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
import com.smathome.SmartHome.Agent.AgentePericoli;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class GasSensorController {
	
    private final DeviceService deviceService;
    private final RilevationService rilevationService;

    @Autowired
    public GasSensorController(RoomService roomService, DeviceService deviceService, RilevationService rilevationService){
        this.deviceService = deviceService;
        this.rilevationService = rilevationService;
    }
    
    @PostMapping("/gasSensor")
    public void receiveSensorData(@RequestBody JSONObject jsonData) {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);
    	if(AgentiStatus.getPericoli() && rilevation.getValue() == 1.0) {
    		Agente agente = new AgentePericoli(rilevation, deviceService);
    		agente.run();
    	}
    }
    
    
}


