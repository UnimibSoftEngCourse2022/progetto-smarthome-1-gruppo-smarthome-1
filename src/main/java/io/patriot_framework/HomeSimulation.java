/*
 * Copyright 2019 Patriot project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.patriot_framework;

import io.patriot_framework.generator.dataFeed.ConstantDataFeed;
import io.patriot_framework.generator.dataFeed.DataFeed;
import io.patriot_framework.generator.dataFeed.DayTemperatureDataFeed;
import io.patriot_framework.generator.dataFeed.NormalDistributionDataFeed;
import io.patriot_framework.generator.device.active.ActiveDevice;
import io.patriot_framework.generator.device.active.ActiveDeviceImpl;
import io.patriot_framework.generator.device.actuators.*;
import io.patriot_framework.generator.device.impl.basicActuators.LinearActuator;
import io.patriot_framework.generator.device.impl.basicDevices.Thermometer;
import io.patriot_framework.generator.device.passive.actuators.Actuator;
import io.patriot_framework.generator.device.passive.sensors.SimpleSensor;
import io.patriot_framework.generator.network.NetworkAdapter;
import io.patriot_framework.generator.network.Rest;
import io.patriot_framework.generator.network.wrappers.JSONWrapper;

public class HomeSimulation {

    public static void main(String[] args) {
        String testEndopint = "http://localhost:8080/api/v1/sensor/";

        // Definizione metodologia di generazione dati (criterio di randomizzazione di dati)
        DataFeed df = new NormalDistributionDataFeed(15, 2);
        
        NetworkAdapter na = new Rest(testEndopint, new JSONWrapper());

        // dispositivo effettivo (statico)
        SimpleSensor temperature = new Thermometer("thermometer1", df);
        //df.setLabel(temperature.getLabel());
        temperature.setNetworkAdapter(na);
        temperature.startCoapController();

        // Definisco intervallo di generazione valori
        DataFeed tf = new ConstantDataFeed(10000);

        // Simula effettivamente il device
        ActiveDevice simulation = new ActiveDeviceImpl(tf, temperature);
        simulation.start();

        //
        // ATTUATORI
        //
        Actuator[] luci = new Actuator[6];

        for(int i = 0; i < luci.length; i++)
        {
            luci[i] = new Luce("luce" + (i + 1));
            luci[i].startCoapController();
        }

        Actuator[] condizionatori = new Actuator[5];

        for(int i = 0; i < condizionatori.length; i++)
        {
            condizionatori[i] = new Temperatura("condizionatore" + (i + 1));
            condizionatori[i].startCoapController();
        }

        Actuator[] termosifoni = new Actuator[6];

        for(int i = 0; i < termosifoni.length; i++)
        {
            termosifoni[i] = new Temperatura("termosifone" + (i + 1));
            termosifoni[i].startCoapController();
        }

        Actuator[] finestre = new Actuator[5];
        Actuator[] tapparelle = new Actuator[5];

        for(int i = 0; i < finestre.length; i++)
        {
            finestre[i] = new Finestra("finestra" + (i + 1));
            finestre[i].startCoapController();

            tapparelle[i] = new Tapparella("tapparella" + (i + 1));
            tapparelle[i].startCoapController();
        }

        Actuator[] porte = new Actuator[5];

        for(int i = 0; i < porte.length; i++)
        {
            porte[i] = new Finestra("porta" + (i + 1));
            porte[i].startCoapController();
        }

        Actuator pulizia = new Pulizia("pulizia");
        pulizia.startCoapController();
    }

}
