package com.smathome.SmartHome.Agent.Strategy;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;

public class StrategySmoke implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		// TODO Auto-generated method stub
		boolean signal = rilevazione.getValue() == 1.0;

		if(signal)
		{
			
		}
	}

}
