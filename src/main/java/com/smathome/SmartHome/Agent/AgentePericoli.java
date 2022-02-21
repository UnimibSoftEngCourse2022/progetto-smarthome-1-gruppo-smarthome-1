package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.Device.Category;
import com.smarthome.SmartHome.Device.Device;
import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.Context;
import com.smathome.SmartHome.Agent.Strategy.StrategyGas;
import com.smathome.SmartHome.Agent.Strategy.StrategySmoke;

public class AgentePericoli extends Agente {

	public AgentePericoli(Rilevation rilevazione, DeviceService deviceService) {
		super(rilevazione, deviceService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Context context = new Context();
		Device sensor = rilevazione.getDevice();
		if(sensor.getCategory() == Category.GAS) {
			context.setStrategy(new StrategyGas());
		} else {
			context.setStrategy(new StrategySmoke());
		}
		context.runStrategy(rilevazione, deviceService);
	}
	
}
