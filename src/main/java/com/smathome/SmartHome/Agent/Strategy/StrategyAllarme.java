package com.smathome.SmartHome.Agent.Strategy;

import java.util.List;

import com.smarthome.SmartHome.device.Actuator;
import com.smarthome.SmartHome.device.Category;
import com.smarthome.SmartHome.device.Device;
import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.AgentiStatus;

public class StrategyAllarme implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		boolean signal = rilevazione.getValue() == 1.0;

		if(signal)
			AgentiStatus.setAllarme(signal);
			Device sensor = rilevazione.getDevice();
			List<Device> devices = deviceService.getAllDevices();
			for(Device device : devices) {
				if(device.getCategory() == Category.SIRENA);
					Actuator sirena = (Actuator) device;
					String state = sirena.getCurrentState();
					if(state.equals("OFF") || state.equals("Spegnimento")) {
						sirena.controlSignal();
					}
				
			}
	}
}
