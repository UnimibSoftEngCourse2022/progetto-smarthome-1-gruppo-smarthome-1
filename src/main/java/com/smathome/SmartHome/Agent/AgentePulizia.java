package com.smathome.SmartHome.Agent;

import com.smarthome.smarthome.device.DeviceService;
import com.smarthome.smarthome.rilevation.Rilevation;

public class AgentePulizia extends Agente
{
	public AgentePulizia(Rilevation rilevazione, DeviceService deviceService)
	{
		super(rilevazione, deviceService);
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		// crea un oggetto context passado come parametro lo strategy corretto
		// esegue context.runStrategy(this.rilevazione)	
	}
}
