package com.smarthome.SmartHome.application;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smathome.SmartHome.Agent.Agente;
import com.smathome.SmartHome.Agent.AgenteAllarme;
import com.smathome.SmartHome.Agent.AgenteLuce;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class MovementSensorController extends Controller
{
    @Autowired
    public MovementSensorController(DeviceService deviceService, RilevationService rilevationService)
    {
        super(deviceService, rilevationService);
    }
    
    @PostMapping("/movementSensor")
    public void receiveSensorData(@RequestBody JSONObject jsonData)
    {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);

    	if(AgentiStatus.getAllarme())
        {
    		Agente agente = new AgenteAllarme(rilevation, deviceService);
    		agente.run();
    	}
        else
        {
    		Agente agente = new AgenteLuce(rilevation, deviceService);
    		agente.run();
    	}
    }
    
    
}

