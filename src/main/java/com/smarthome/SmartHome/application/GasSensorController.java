package com.smarthome.SmartHome.application;

import java.util.Calendar;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smarthome.SmartHome.device.Device;
import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.emergenza.EmergencyCode;
import com.smarthome.SmartHome.emergenza.Emergenza;
import com.smarthome.SmartHome.emergenza.EmergenzaRepository;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smarthome.SmartHome.rilevation.RilevationService;
import com.smarthome.SmartHome.room.RoomService;
import com.smathome.SmartHome.Agent.Agente;
import com.smathome.SmartHome.Agent.AgentePericoli;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class GasSensorController extends Controller
{
	private EmergenzaRepository emergenzaRepo;
    @Autowired
    public GasSensorController(RoomService roomService, DeviceService deviceService, RilevationService rilevationService, EmergenzaRepository emergenzaRepo)
    {
        super(deviceService, rilevationService);
        this.emergenzaRepo = emergenzaRepo;
    }
    
    @PostMapping("/gasSensor")
    public void receiveSensorData(@RequestBody JSONObject jsonData)
    {
    	Rilevation rilevation = new Rilevation(jsonData, deviceService, rilevationService);

    	if(AgentiStatus.getPericoli() && rilevation.getValue() == 1.0)
        {	
    		Agente agente = new AgentePericoli(rilevation, deviceService);
    		agente.run();
    		
    		Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            Device sensor = rilevation.getDevice();
    	    Emergenza e = new Emergenza(EmergencyCode.INTRUSIONE, currentTimestamp, sensor.getRoom());
    	    emergenzaRepo.save(e);
    	}
    }
    
    
}


