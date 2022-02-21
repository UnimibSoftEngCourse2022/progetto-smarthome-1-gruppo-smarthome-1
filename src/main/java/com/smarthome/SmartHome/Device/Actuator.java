package com.smarthome.SmartHome.Device;


import com.smarthome.SmartHome.room.Room;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

import java.net.URI;

public class Actuator extends Device
{
    public Actuator() {}

    public Actuator(Long id, String label, Category category, Room room) {
        super(id, label, category, room, true);
    }

    public Actuator(String label, Category category, Room room) {
        super(label, category, room, true);
    }

    public void controlSignal()
    {
        try
        {
            URI uri = new URI("coap://localhost:5683/" + getLabel());
            CoapClient client = new CoapClient(uri);

            client.post("", 0);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    public String getCurrentState()
    {
        String value = "";

        try
        {
            URI uri = new URI("coap://localhost:5683/" + getLabel());
            CoapClient client = new CoapClient(uri);

            CoapResponse response = client.get();
            value = response.getResponseText();
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

        return value;
    }
}
