package com.smathome.SmartHome.Agent.Strategy;

import java.util.List;

import com.smarthome.SmartHome.Device.Actuator;
import com.smarthome.SmartHome.Device.Category;
import com.smarthome.SmartHome.Device.Device;
import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.AgenteTemperatura;

public class StrategyTemperaturaInverno implements Strategy{

	@Override
	public void execute(Rilevation rilevazione, DeviceService deviceService) {
		// TODO Auto-generated method stub
		double target = AgenteTemperatura.getTemperatura();
		if(rilevazione.getValue() < target - 1) {
			Device sensor = rilevazione.getDevice();
			List<Device> devices = deviceService.getDeviceByRoom(sensor.getRoom());
			for(Device device : devices) {
				if(device.getCategory() == Category.TERMOSIFONE) {
					Actuator termosifone = (Actuator) device;
					String state = termosifone.getCurrentState();
					if(state.equals("OFF") || state.equals("Spegnimento"))
						termosifone.controlSignal();
				}
			}
			
		}
		
		
	}

}
