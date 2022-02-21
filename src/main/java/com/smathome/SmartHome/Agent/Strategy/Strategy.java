package com.smathome.SmartHome.Agent.Strategy;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;

public interface Strategy {

	public abstract void execute(Rilevation rilevazione, DeviceService deviceService);
}
