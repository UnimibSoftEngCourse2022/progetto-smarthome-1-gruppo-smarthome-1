package com.smarthome.smarthome.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @DeleteMapping(path="{deviceId}")
    public ResponseEntity<String> deleteDevice(@PathVariable("deviceId") Long deviceId){

        try
        {
            deviceService.deleteDevice(deviceId);
            return new ResponseEntity<>("Device rimosso con successo", HttpStatus.CREATED);
        }
        catch(IllegalStateException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
