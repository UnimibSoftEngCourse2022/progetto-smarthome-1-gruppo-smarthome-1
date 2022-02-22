package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.Strategy;
import com.smathome.SmartHome.Agent.Strategy.StrategyAllarme;

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