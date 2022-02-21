package com.smathome.SmartHome.Agent;

import java.util.Calendar;

import com.smarthome.SmartHome.Device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.Context;
import com.smathome.SmartHome.Agent.Strategy.StrategyTemperaturaEstate;
import com.smathome.SmartHome.Agent.Strategy.StrategyTemperaturaInverno;

public class AgenteTemperatura extends Agente{
	private static double temperatura;
	public AgenteTemperatura(Rilevation rilevazione, DeviceService deviceService) {
		super(rilevazione, deviceService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Context context = new Context(); 
		int month = Calendar.MONTH;
		if( 5 <= month && month <= 9) {
			context.setStrategy(new StrategyTemperaturaEstate());
		} else {
			context.setStrategy(new StrategyTemperaturaInverno());
		}
		context.runStrategy(rilevazione, deviceService);
	}

	public static double getTemperatura() {
		return temperatura;
	}

	public static void setTemperatura(double temperatura) {
		AgenteTemperatura.temperatura = temperatura;
	}

}
