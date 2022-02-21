package com.smarthome.SmartHome.application;

import com.smarthome.SmartHome.Device.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONArray;
import java.util.List;

@Controller
@RequestMapping()
public class ApplicationController
{
    private final DeviceService deviceService;

    @Autowired
    public ApplicationController(DeviceService deviceService)
    {
        this.deviceService = deviceService;
    }

    @GetMapping(path = "/api/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Device>> getAllDevices()
    {
        List<Device> ld = deviceService.getAllDevices();
        JSONArray result = new JSONArray();
        for (Device device : ld)
            result.put(device.toString());

        JSONObject resultData = new JSONObject();
        resultData.put("data", result);

        return new ResponseEntity<List<Device>>(ld, HttpStatus.OK);
    }



    @GetMapping(path = "api/v1/sensor/{label}/data")
    public String getSensorData(@PathVariable("label") String label)
    {
        Sensor s = (Sensor) deviceService.getDeviceByLabel(label);
        if (s != null)
            return String.valueOf(s.getDataFeed());
        else
            return "";
    }

    @GetMapping(path = "api/v1/actuator/{label}/state")
    public String getActuatorCurrentState(@PathVariable("label") String label)
    {
        Actuator a = (Actuator) deviceService.getDeviceByLabel(label);

        if (a != null)
            return a.getCurrentState();
        else
            return "";
    }

    @PostMapping(path = "api/v1/actuator/{label}/signal")
    public String actuatorControlSignal(@PathVariable("label") String label)
    {
        Actuator a = (Actuator) deviceService.getDeviceByLabel(label);

        if (a != null)
        {
            a.controlSignal();
            return "Success";
        }
        else
            return "";
    }
}
