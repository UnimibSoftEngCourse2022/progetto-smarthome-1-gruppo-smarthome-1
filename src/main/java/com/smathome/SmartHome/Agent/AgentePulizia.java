package com.smathome.SmartHome.Agent;



import java.util.TimerTask;

import com.smarthome.smarthome.device.Actuator;

public class AgentePulizia extends TimerTask
{	
	private Actuator puliziaBot;
	private long delay;
	private long period;
	
	public AgentePulizia(Actuator puliziaBot, long delay, long period) {
		this.puliziaBot = puliziaBot;
		this.delay = delay;
		this.period = period;
	}
	@Override
	public void run()
	{
		if(puliziaBot.getCurrentState().equals("In Carica")) {
			puliziaBot.controlSignal();
		}
	}
	
	public long getDelay(){
		return this.delay;
	}
	
	public long getPeriod(){
		return this.period;
	}
	
}
