package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.rilevation.Rilevation;

public class Context {
	private Strategy strategy;
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void runStrategy(Rilevation rilevazione) {
		this.strategy.execute(rilevazione);
	}
	
}
