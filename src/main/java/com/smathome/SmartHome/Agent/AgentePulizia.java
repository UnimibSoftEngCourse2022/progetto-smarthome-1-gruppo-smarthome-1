package com.smathome.SmartHome.Agent;


import com.smarthome.smarthome.device.DeviceService;
import com.smarthome.smarthome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.StrategyPulizia;
import com.smathome.SmartHome.Agent.Strategy.Strategy;

public class AgentePulizia extends Agente
{	
	
	public AgentePulizia(Rilevation rilevazione, DeviceService deviceService)
	{
		super(rilevazione, deviceService);
	}

	@Override
	public void run()
	{
		
	}
	
}
