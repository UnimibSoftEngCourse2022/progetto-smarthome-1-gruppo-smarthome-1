package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.Device.Category;
import com.smarthome.SmartHome.Device.Device;
import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.Strategy;
import com.smathome.SmartHome.Agent.Strategy.StrategyGas;
import com.smathome.SmartHome.Agent.Strategy.StrategySmoke;

public class AgentePericoli extends Agente
{
	public AgentePericoli(Rilevation rilevazione, DeviceService deviceService)
	{
		super(rilevazione, deviceService);
	}

	@Override
	public void run()
	{
		Strategy strategy;
		Device sensor = rilevazione.getDevice();

		if(sensor.getCategory() == Category.GAS)
			strategy = new StrategyGas();
		else
			strategy = new StrategySmoke();

		strategy.execute(rilevazione, deviceService);
	}
}