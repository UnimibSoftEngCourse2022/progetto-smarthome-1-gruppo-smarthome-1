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
import io.patriot_framework.generator.dataFeed.NormalDistributionDataFeed;
import io.patriot_framework.generator.device.active.ActiveDevice;
import io.patriot_framework.generator.device.active.ActiveDeviceImpl;
import io.patriot_framework.generator.device.impl.basicActuators.LinearActuator;
import io.patriot_framework.generator.device.impl.basicDevices.Thermometer;
import io.patriot_framework.generator.device.passive.actuators.Actuator;
import io.patriot_framework.generator.device.passive.sensors.SimpleSensor;
import io.patriot_framework.generator.network.NetworkAdapter;
import io.patriot_framework.generator.network.Rest;
import io.patriot_framework.generator.network.wrappers.JSONWrapper;

public class HomeSimulation {

    public static void main(String[] args) {
        String testEndopint = "http://localhost:8080/api/v1/testPatriot/";

        // Definizione metodologia di generazione dati (criterio di randomizzazione di dati)
        DataFeed df = new NormalDistributionDataFeed(18, 2);
        NetworkAdapter na = new Rest(testEndopint, new JSONWrapper());

        // dispositivo effettivo (statico)
        SimpleSensor temperature = new Thermometer("thermometer", df);
        temperature.setNetworkAdapter(na);
        temperature.startCoapController();

        // Definisco intervallo di generazione valori
        DataFeed tf = new ConstantDataFeed(10000);

        // Simula effettivamente il device
        ActiveDevice simulation = new ActiveDeviceImpl(tf, temperature);
        simulation.start();


        Actuator actuator = new LinearActuator("tapparella", 15000);

        //actuator.controlSignal();

        //ActuatorResource acc = new ActuatorResource(actuator);
        //acc.registerDevice();

        //actuator.setCoapController(acc);
        actuator.startCoapController();


    }

}
