package com.smarthome.smarthome;

import com.smarthome.smarthome.device.*;
import com.smarthome.smarthome.emergenza.EmergencyCode;
import com.smarthome.smarthome.emergenza.Emergenza;
import com.smarthome.smarthome.emergenza.EmergenzaRepository;
import com.smarthome.smarthome.rilevation.RilevationRepository;
import com.smarthome.smarthome.room.Room;
import com.smarthome.smarthome.room.RoomRepository;
import com.smarthome.smarthome.user.User;
import com.smarthome.smarthome.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepo, RoomRepository roomRepo, DeviceRepository deviceRepo, RilevationRepository rilevationRepo, EmergenzaRepository emergencyRepo){
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
			Room r4 = new Room("Studio");
			roomRepo.saveAll(List.of(r1, r2, r3, r4));

			// Create devices
			Device thermometer1 = new Device("thermometer1", Category.TERMOMETRO, r1, false);
			Device thermometer2 = new Device("thermometer2", Category.TERMOMETRO, r2, false);

			Device termosifone1 = new Device("tapparella", Category.TAPPARELLA, r2, true);

			deviceRepo.saveAll(List.of(thermometer1, thermometer2, termosifone1));


			//Emergenze demo
			Date date = new Date();
			Emergenza e1 = new Emergenza(EmergencyCode.INTRUSIONE, new Timestamp(date.getTime()), r1);
			emergencyRepo.save(e1);
		};
	}


}
