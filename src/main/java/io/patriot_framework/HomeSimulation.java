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

import io.patriot_framework.generator.dataFeed.*;
import io.patriot_framework.generator.device.active.ActiveDevice;
import io.patriot_framework.generator.device.active.ActiveDeviceImpl;
import io.patriot_framework.generator.device.actuators.*;
import io.patriot_framework.generator.device.passive.actuators.Actuator;

public class HomeSimulation {

    public static void main(String[] args) {
    	// Definizione metodologia di generazione dati (criterio di randomizzazione di dati)

        DataFeed dfTemperatura_2 = new DayTemperatureDataFeed(15, 22);



        
        //
        // SENSORI
        //
        ActiveDevice[] termometri = new ActiveDeviceImpl[5];
        DataFeed dfTemperatura;

        for(int i = 1; i <= termometri.length; i++)
        {
            dfTemperatura = new NormalDistributionDataFeed(20, 1);
            termometri[i - 1] = new ActiveDeviceImpl("thermometer" + i, "thermometer", dfTemperatura, 2000);
            termometri[i - 1].start();
        }

        ActiveDevice[] sensoriMovimento = new ActiveDeviceImpl[5];
        DataFeed dfMovement;
        for(int i = 1; i <= sensoriMovimento.length; i++)
        {
            dfMovement = new BinaryDataFeed(3);
            sensoriMovimento[i - 1] = new ActiveDeviceImpl("movement" + i, "movementSensor", dfMovement, 2000);
            sensoriMovimento[i - 1].start();
        }

        ActiveDevice[] sensoriFumo = new ActiveDeviceImpl[5];
        DataFeed df_smoke_gas;
        for(int i = 1; i <= sensoriFumo.length; i++)
        {
            df_smoke_gas = new BinaryDataFeed(10);
            sensoriFumo[i - 1] = new ActiveDeviceImpl("smoke" + i, "smokeSensor", df_smoke_gas, 2000);
            sensoriFumo[i - 1].start();
        }

        ActiveDevice[] sensoriGas = new ActiveDeviceImpl[5];

        for(int i = 1; i <= sensoriGas.length; i++)
        {
            df_smoke_gas = new BinaryDataFeed(10);
            sensoriGas[i - 1] = new ActiveDeviceImpl("gas" + i, "gasSensor", df_smoke_gas, 2000);
            sensoriGas[i - 1].start();
        }

        //
        // ATTUATORI
        //
        Actuator[] luci = new Actuator[6];

        for(int i = 1; i <= luci.length; i++)
        {
            luci[i - 1] = new Luce("luce" + i);
            luci[i - 1].startCoapController();
        }

        Actuator[] condizionatori = new Actuator[5];

        for(int i = 1; i <= condizionatori.length; i++)
        {
            condizionatori[i - 1] = new Condizionatore("condizionatore" + i);
            condizionatori[i - 1].startCoapController();
        }

        Actuator[] termosifoni = new Actuator[6];

        for(int i = 1; i <= termosifoni.length; i++)
        {
            termosifoni[i - 1] = new Termosifone("termosifone" + i);
            termosifoni[i - 1].startCoapController();
        }

        Actuator[] finestre = new Actuator[5];

        for(int i = 1; i <= finestre.length; i++)
        {
            finestre[i - 1] = new Finestra("finestra" + i);
            finestre[i - 1].startCoapController();
        }

        Actuator pulizia = new Pulizia("pulizia");
        pulizia.startCoapController();
    }
}
