package com.smathome.SmartHome.Agent;

import com.smarthome.SmartHome.rilevation.Rilevation;

public class AgenteTemperatura extends Agente{

	public AgenteTemperatura(Rilevation rilevazione) {
		super(rilevazione);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// crea un oggetto context passado come parametro lo strategy corretto
		// esegue context.runStrategy(this.rilevazione)	
	}

}