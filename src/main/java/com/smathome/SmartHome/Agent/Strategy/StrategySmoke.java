package com.smathome.SmartHome.Agent.Strategy;

import com.smarthome.smarthome.device.DeviceService;
import com.smarthome.smarthome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class StrategySmoke implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		// TODO Auto-generated method stub
		boolean signal = rilevazione.getValue() == 1.0;

		if(signal)
		{
			AgentiStatus.setTemperatura(!signal);
		}
	}

}
