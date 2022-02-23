package com.smathome.SmartHome.Agent.Strategy;

import java.util.List;

import com.smarthome.SmartHome.device.Actuator;
import com.smarthome.SmartHome.device.Category;
import com.smarthome.SmartHome.device.Device;
import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.AgenteTemperatura;

public class StrategyTemperaturaEstate implements Strategy
{
	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService)
	{
		// TODO Auto-generated method stub
		double target = AgenteTemperatura.getTemperatura();

		if(rilevazione.getValue() > target - 1)
		{
			Device sensor = rilevazione.getDevice();
			List<Device> devices = deviceService.getDeviceByRoom(sensor.getRoom());

			for(Device device : devices)
			{
				if(device.getCategory() == Category.CONDIZIONATORE)
				{
					Actuator condizionatore = (Actuator) device;
					String state = condizionatore.getCurrentState();

					if(state.equals("OFF") || state.equals("Spegnimento"))
						condizionatore.controlSignal();
				}
			}
		}
	}
}
