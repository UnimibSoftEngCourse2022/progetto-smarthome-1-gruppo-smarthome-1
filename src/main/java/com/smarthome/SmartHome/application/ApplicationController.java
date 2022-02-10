package com.smarthome.SmartHome.application;

import com.smarthome.SmartHome.Device.*;
import com.smarthome.SmartHome.room.Room;
import com.smarthome.SmartHome.room.RoomService;
import com.smarthome.SmartHome.user.User;
import com.smarthome.SmartHome.user.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ApplicationController {
    private final RoomService roomService;
    private final UserService userService;
    private final DeviceService deviceService;

    @Autowired
    public ApplicationController(RoomService roomService, UserService userService, DeviceService deviceService){
        this.roomService = roomService;
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping
    public String index(){
        List<User> users = userService.getUsers();
        List<Room> rooms = roomService.getRooms();

        String result = "";
        result += "<h1>SmartHome Application</h1>";
        result += "<h2>Users</h2>" + users.toString();

        result += "<h2>Rooms</h2>";

        for (Room room: rooms) {
            result += "<h3>" + room.getName() + "</h3>";
            List<Device> devices = deviceService.getDeviceByRoom(room);

            for (Device device: devices) {
                result += "<ul>" + device.getLabel() + "</ul>";
            }
        }


        return result;
    }

    @PostMapping(path = "/api/v1/sensor")
    public void reciveSensorData(@RequestBody JSONObject jasonData) {


        System.out.println(jasonData);
    }

    @GetMapping(path = "api/v1/test")
    public String testMethod() {
        /*Room testRoom = new Room("test");
        Actuator testActuator = new Actuator("tapparella", Category.LUCE,testRoom);

        testActuator.controlSignal();

        return testActuator.getCurrentState();*/

        Room testRoom = new Room("test");
        Sensor testSensor = new Sensor("thermometer1", Category.TEMPERATURA, testRoom);

        double result = testSensor.getDataFeed();

        return String.valueOf(result);



    }

}
