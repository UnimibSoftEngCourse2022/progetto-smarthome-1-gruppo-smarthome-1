package com.smathome.SmartHome.Agent;

import java.util.Calendar;

import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.Rilevation;
import com.smathome.SmartHome.Agent.Strategy.Strategy;
import com.smathome.SmartHome.Agent.Strategy.StrategyTemperaturaEstate;
import com.smathome.SmartHome.Agent.Strategy.StrategyTemperaturaInverno;

public class AgenteTemperatura extends Agente
{
	private static double temperatura;

	public AgenteTemperatura(Rilevation rilevazione, DeviceService deviceService)
	{
		super(rilevazione, deviceService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run()
	{
		Strategy strategy;
		int month = Calendar.MONTH;

		if( 5 <= month && month <= 9)
			strategy = new StrategyTemperaturaEstate();
		else
			strategy = new StrategyTemperaturaInverno();

		strategy.execute(rilevazione, deviceService);
	}

	public static double getTemperatura()
	{
		return temperatura;
	}

	public static void setTemperatura(double temperatura)
	{
		AgenteTemperatura.temperatura = temperatura;
	}
}