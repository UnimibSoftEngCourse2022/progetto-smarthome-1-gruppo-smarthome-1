package com.smarthome.SmartHome.room;

import javax.persistence.*;
import org.json.JSONObject;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long id;
    private String name;

    public Room(){

    }

    public Room(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Room( String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        JSONObject jo=new JSONObject();
        
        jo.put("room", new JSONObject().put("id", id).put("name", name));
        
        return jo.toString();
    }
}
