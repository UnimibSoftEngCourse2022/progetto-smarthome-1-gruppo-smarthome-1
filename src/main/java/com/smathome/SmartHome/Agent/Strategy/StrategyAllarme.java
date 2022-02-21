package com.smathome.SmartHome.Agent.Strategy;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class StrategyAllarme implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		// TODO Auto-generated method stub
		boolean signal = rilevazione.getValue() == 1.0;

		if(signal)
			AgentiStatus.setAllarme(signal);
	}
}
