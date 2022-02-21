package com.smathome.SmartHome.Agent.Strategy;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;

public class Context {
	private Strategy strategy;
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void runStrategy(Rilevation rilevazione, DeviceService deviceService) {
		this.strategy.execute(rilevazione, deviceService);
	}
	
}
