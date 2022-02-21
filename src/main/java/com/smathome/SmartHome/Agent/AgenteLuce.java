package com.smathome.SmartHome.Agent;

import java.time.LocalTime;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.Context;
import com.smathome.SmartHome.Agent.Strategy.StrategyAllarme;
import com.smathome.SmartHome.Agent.Strategy.StrategyLuceGiorno;

public class AgenteLuce extends Agente {
	
	public AgenteLuce(Rilevation rilevazione, DeviceService deviceService) {
		super(rilevazione, deviceService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		int localTime = LocalTime.now().getHour();
		Context context = new Context();
		if( localTime >= 7 && localTime <= 23) {
			context.setStrategy(new StrategyLuceGiorno());
			context.runStrategy(this.rilevazione, this.deviceService);
		}
	}

}
