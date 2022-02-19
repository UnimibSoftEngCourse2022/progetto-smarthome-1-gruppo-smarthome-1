package com.smarthome.SmartHome.application;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smathome.SmartHome.Agent.Agente;
import com.smathome.SmartHome.Agent.AgentePericoli;

public class SmokeSensorController {
	
    private final DeviceService deviceService;
    private final RilevationService rilevationService;

    @Autowired
    public SmokeSensorController(DeviceService deviceService, RilevationService rilevationService){
        this.deviceService = deviceService;
        this.rilevationService = rilevationService;
    }
    
    @PostMapping("/smokeSensor")
    public void receiveSensorData(@RequestBody JSONObject jsonData) {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);
    	if(AgentePericoli.getStatus()) {
    		Agente agente = new AgentePericoli(rilevation);
    		agente.run();
    	}
    }    
}