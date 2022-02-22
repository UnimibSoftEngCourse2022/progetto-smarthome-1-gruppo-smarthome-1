package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;

public abstract class Agente
{
	protected Rilevation rilevazione;
	protected DeviceService deviceService;
	
	public Agente(Rilevation rilevazione, DeviceService deviceService)
	{
		this.rilevazione = rilevazione;
		this.deviceService = deviceService;
	}
	
	public abstract void run();
}
