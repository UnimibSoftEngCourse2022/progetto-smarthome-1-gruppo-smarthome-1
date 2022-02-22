package com.smarthome.SmartHome.application;

import java.util.Calendar;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smarthome.SmartHome.Device.Device;
import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.emergenza.EmergencyCode;
import com.smarthome.SmartHome.emergenza.Emergenza;
import com.smarthome.SmartHome.emergenza.EmergenzaRepository;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smathome.SmartHome.Agent.Agente;
import com.smathome.SmartHome.Agent.AgentePericoli;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class SmokeSensorController extends Controller
{
    private EmergenzaRepository emergenzaRepo;

	@Autowired
    public SmokeSensorController(DeviceService deviceService, RilevationService rilevationService, EmergenzaRepository emergenzaRepo)
    {
        super(deviceService, rilevationService);
        this.emergenzaRepo = emergenzaRepo;
    }
    
    @PostMapping("/smokeSensor")
    public void receiveSensorData(@RequestBody JSONObject jsonData)
    {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);

    	if(AgentiStatus.getPericoli() && rilevation.getValue() == 1.0)
        {	
    		Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            Device sensor = rilevation.getDevice();
    	    Emergenza e = new Emergenza(EmergencyCode.FUMO, currentTimestamp, sensor.getRoom());
    	    emergenzaRepo.save(e);
    		Agente agente = new AgentePericoli(rilevation, deviceService);
    		agente.run();
    	}
    }    
}