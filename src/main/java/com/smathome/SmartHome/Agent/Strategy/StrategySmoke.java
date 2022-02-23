package com.smathome.SmartHome.Agent.Strategy;

import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class StrategySmoke implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		boolean signal = rilevazione.getValue() == 1.0;

		if(signal)
		{
			AgentiStatus.setTemperatura(!signal);
		}
	}

}
