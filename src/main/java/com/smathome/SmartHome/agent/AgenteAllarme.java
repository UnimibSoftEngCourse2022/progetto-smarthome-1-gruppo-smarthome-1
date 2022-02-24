package com.smathome.SmartHome.agent;

import com.smarthome.smarthome.device.DeviceService;
import com.smarthome.smarthome.rilevation.Rilevation;
import com.smathome.SmartHome.agent.strategy.Strategy;
import com.smathome.SmartHome.agent.strategy.StrategyAllarme;

public class AgenteAllarme extends Agente
{	
	public AgenteAllarme(Rilevation rilevazione, DeviceService deviceService)
	{
		super(rilevazione, deviceService);
	}

	@Override
	public void run()
	{
		Strategy strategy = new StrategyAllarme();
		strategy.execute(this.rilevazione, this.deviceService);
	}
}