package com.smathome.SmartHome.Agent;

import java.util.concurrent.atomic.AtomicBoolean;

import com.smarthome.SmartHome.rilevation.Rilevation;

public abstract class Agente {
	private static AtomicBoolean status = new AtomicBoolean(true);
	private Rilevation rilevazione;
	
	public Agente(Rilevation rilevazione) {
		this.rilevazione = rilevazione;
	}
	
	public static Boolean getStatus() {
		return status.get();
	}
	public static void setStatus(Boolean flag) {
		status.set(flag);
	}
	
	public abstract void run();
}
