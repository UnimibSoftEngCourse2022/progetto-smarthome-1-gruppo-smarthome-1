package com.smarthome.SmartHome.application;

import com.smarthome.SmartHome.device.DeviceService;
import com.smarthome.SmartHome.rilevation.RilevationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class Controller
{
    DeviceService deviceService;
    RilevationService rilevationService;

    @Autowired
    public Controller(DeviceService deviceService, RilevationService rilevationService)
    {
        this.deviceService = deviceService;
        this.rilevationService = rilevationService;
    }

    public abstract void receiveSensorData(@RequestBody JSONObject jsonData);
}
