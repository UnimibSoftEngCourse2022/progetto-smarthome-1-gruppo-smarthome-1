package com.smarthome.SmartHome.Device;

import com.smarthome.SmartHome.room.Room;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

import java.net.URI;

public class Sensor extends Device{

    public Sensor() {
    }

    public Sensor(Long id, String label, Category category, Room room) {
        super(id, label, category, room);
    }

    public Sensor(String label, Category category, Room room) {
        super(label, category, room);
    }

    public double getDataFeed(){
        double value = -1.0;

        try {
            URI uri = new URI("coap://localhost:5683/" + getLabel());
            CoapClient client = new CoapClient(uri);

            CoapResponse response = client.get();
            value = Double.parseDouble(response.getResponseText());
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return value;
    }
}
