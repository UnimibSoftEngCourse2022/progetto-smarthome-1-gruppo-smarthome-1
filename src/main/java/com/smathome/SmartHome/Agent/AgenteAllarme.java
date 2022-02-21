package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.Context;
import com.smathome.SmartHome.Agent.Strategy.StrategyAllarme;

public class AgenteAllarme extends Agente {
	public AgenteAllarme(Rilevation rilevazione, DeviceService deviceService) {
		super(rilevazione, deviceService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Context context = new Context();
		context.setStrategy(new StrategyAllarme());
		context.runStrategy(this.rilevazione, this.deviceService);
	}
}
