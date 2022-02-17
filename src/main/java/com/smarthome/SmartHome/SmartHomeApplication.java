package com.smarthome.SmartHome;

import com.smarthome.SmartHome.Device.*;
import com.smarthome.SmartHome.rilevation.RilevationRepository;
import com.smarthome.SmartHome.room.Room;
import com.smarthome.SmartHome.room.RoomRepository;
import com.smarthome.SmartHome.user.User;
import com.smarthome.SmartHome.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepo, RoomRepository roomRepo, DeviceRepository deviceRepo, RilevationRepository rilevationRepo){
		return args -> {
			// Create users
			User pippo = new User(
					"pippo",
					"pippo@demo.com"
			);
			User pluto = new User(
					"pluto",
					"pluto@demo.com"
			);
			userRepo.saveAll(List.of(pippo, pluto));

			// Create rooms
			Room r1 = new Room("Cucina");
			Room r2 = new Room("Soggiorno");
			Room r3 = new Room("Bagno");
			roomRepo.saveAll(List.of(r1, r2, r3));

			// Create devices
			Device thermometer1 = new Device("thermometer1", Category.TEMPERATURA, r1, false);
			Device thermometer2 = new Device("thermometer2", Category.TEMPERATURA, r2, false);

			Device termosifone1 = new Device("tapparella", Category.TEMPERATURA, r2, true);

			deviceRepo.saveAll(List.of(thermometer1, thermometer2, termosifone1));

		};
	}


}
