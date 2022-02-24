package com.smathome.SmartHome.agent.strategy;

import java.util.List;

import com.smarthome.smarthome.device.Actuator;
import com.smarthome.smarthome.device.Category;
import com.smarthome.smarthome.device.Device;
import com.smarthome.smarthome.device.DeviceService;
import com.smarthome.smarthome.rilevation.Rilevation;
import com.smathome.SmartHome.agent.AgenteTemperatura;

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
