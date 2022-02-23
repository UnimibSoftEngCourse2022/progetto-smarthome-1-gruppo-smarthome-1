package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;

public class AgentePulizia extends Agente
{
	public AgentePulizia(Rilevation rilevazione, DeviceService deviceService)
	{
		super(rilevazione, deviceService);
	}

	@Override
	public void run()
	{
		// crea un oggetto context passado come parametro lo strategy corretto
		// esegue context.runStrategy(this.rilevazione)	
	}
}
