package com.smathome.SmartHome.Agent.Strategy;

import java.util.List;

import com.smarthome.SmartHome.device.Actuator;
import com.smarthome.SmartHome.device.Category;
import com.smarthome.SmartHome.device.Device;
import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;

public class StrategyLuceGiorno implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		// TODO Auto-generated method stub
		Device sensor = rilevazione.getDevice();
		List<Device> devices = deviceService.getDeviceByRoom(sensor.getRoom());

		for(Device device : devices)
			if(device.getCategory() == Category.LAMPADA && device.isDeviceType())
			{
				Actuator lampada = (Actuator) device;
				String state = lampada.getCurrentState();

				if(state.equals("OFF") || state.equals("Spegnimento"))
				{
					if(rilevazione.getValue() == 1.0)
						lampada.controlSignal();
				}
				else
					if(rilevazione.getValue() == 0.0)
						lampada.controlSignal();
			}
	}
}
