package com.smarthome.SmartHome.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.smarthome.SmartHome.device.*;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smathome.SmartHome.Agent.Agente;
import com.smathome.SmartHome.Agent.AgenteTemperatura;
import com.smathome.SmartHome.Agent.AgentiStatus;

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
    
    @PostMapping(path= "/set-temperature", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rilevation> getLastTemperature() {

        if(AgentiStatus.getTemperatura())
        {
            
        }
        Rilevation r = rilevationService.getLastTemperaturRilevation();

        return new ResponseEntity(r, HttpStatus.OK);
    }
    
    
}
