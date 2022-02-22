package com.smarthome.SmartHome.emergenza;

import java.sql.Timestamp;

import javax.persistence.*;

import com.smarthome.SmartHome.room.Room;


@Entity
@Table(name = "Emergenza")

public class Emergenza {
	private @GeneratedValue @Id long id;
	private Timestamp timeStamp;
	
	@Enumerated(EnumType.STRING)
	private EmergencyCode code;
    
	@ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

	public Emergenza(EmergencyCode code, Timestamp timeStamp, Room r) {
		this.code = code;
		this.timeStamp = timeStamp;
		this.room = r;
	}
	
	public Emergenza(long id, EmergencyCode code, Timestamp timeStamp, Room r) {
		this.code = code;
		this.timeStamp = timeStamp;
		this.room = r;
		this.id = id;
	}
    
    public Timestamp getTimeStamp() {
    	return this.timeStamp;
    }
    
    public EmergencyCode getCode() {
		return this.code;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public long getId() {
		return this.id;
	}
    
    
    
	
	
}
