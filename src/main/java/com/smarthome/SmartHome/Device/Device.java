package com.smarthome.SmartHome.Device;

import com.smarthome.SmartHome.room.Room;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class Device {
    @Id
    @SequenceGenerator(
            name="device_sequence",
            sequenceName="device_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_sequence"
    )
    private Long id;
    private String label;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    public Device(){}

    public Device(Long id, String label, Category category, Room room) {
        this.id = id;
        this.label = label;
        this.category = category;
        this.room = room;
    }

    public Device(String label, Category category, Room room) {
        this.label = label;
        this.category = category;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


}
