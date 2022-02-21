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
import com.smathome.SmartHome.Agent.AgentiStatus;

public class SmokeSensorController extends Controller
{
    @Autowired
    public SmokeSensorController(DeviceService deviceService, RilevationService rilevationService)
    {
        super(deviceService, rilevationService);
    }
    
    @PostMapping("/smokeSensor")
    public void receiveSensorData(@RequestBody JSONObject jsonData)
    {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);

    	if(AgentiStatus.getPericoli())
        {
    		Agente agente = new AgentePericoli(rilevation, deviceService);
    		agente.run();
    	}
    }    
}