package com.smarthome.SmartHome.room;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getAllRooms() {

        List<Room> ld = roomService.getRooms();

        return new ResponseEntity<List<Room>>(ld, HttpStatus.OK);
    }

    @GetMapping(path="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getAllRoomInfo() {

        List<Room> ld = new ArrayList<Room>();

        roomService.getRooms().forEach(ld::add);

        return new ResponseEntity<List<Room>>(ld, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerNewRoom(@RequestBody Room room){
        try {
            roomService.addNewRoom(room);
            return new ResponseEntity("Stanza aggiunta", HttpStatus.CREATED);
        }catch (IllegalStateException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(path = "{roomId}")
    public ResponseEntity deleteRoom(@PathVariable("roomId") Long roomId){
        try {
            roomService.deleteRoom(roomId);
            return new ResponseEntity("Stanza rimossa", HttpStatus.CREATED);
        }catch (IllegalStateException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
