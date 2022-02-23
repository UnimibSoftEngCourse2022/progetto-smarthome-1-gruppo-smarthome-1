package com.smathome.SmartHome.Agent.Strategy;

import java.util.List;

import com.smarthome.SmartHome.device.Actuator;
import com.smarthome.SmartHome.device.Category;
import com.smarthome.SmartHome.device.Device;
import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.AgenteTemperatura;

public class StrategyTemperaturaInverno implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		double target = AgenteTemperatura.getTemperatura();

		if(rilevazione.getValue() < target - 1)
		{
			Device sensor = rilevazione.getDevice();
			List<Device> devices = deviceService.getDeviceByRoom(sensor.getRoom());

			for(Device device : devices)
				if(device.getCategory() == Category.TERMOSIFONE)
				{
					Actuator termosifone = (Actuator) device;
					String state = termosifone.getCurrentState();

					if(state.equals("OFF") || state.equals("Spegnimento"))
						termosifone.controlSignal();
				}
		}
	}
}
