package com.smarthome.smarthome.application;

import org.springframework.beans.factory.annotation.Autowired;

import com.smarthome.smarthome.device.*;
import com.smarthome.smarthome.rilevation.Rilevation;
import com.smarthome.smarthome.rilevation.RilevationService;
import com.smathome.SmartHome.agent.Agente;
import com.smathome.SmartHome.agent.AgenteTemperatura;
import com.smathome.SmartHome.agent.AgentiStatus;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class ThermometerController extends Controller
{
    @Autowired
    public ThermometerController(DeviceService deviceService, RilevationService rilevationService)
    {
        super(deviceService, rilevationService);
    }

    @PostMapping("/thermometer")
    public void receiveSensorData(@RequestBody JSONObject jsonData)
    {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);

        if(AgentiStatus.getTemperatura())
        {
    		Agente agente = new AgenteTemperatura(rilevation, deviceService);
    		agente.run();
    	}
    }
    
    
}
