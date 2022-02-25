package com.smarthome.smarthome;

import com.smarthome.smarthome.emergenza.EmergencyCode;
import com.smarthome.smarthome.emergenza.Emergenza;
import com.smarthome.smarthome.rilevation.Rilevation;
import com.smarthome.smarthome.device.*;
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

import java.util.Calendar;
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
			User u1 = new User(
					"Federico Lanzani",
					"federico@lanzani.com"
			);
			User u2 = new User(
					"Lorenzo Mazzoletti",
					"lorenzo@mazzoletti.com"
			);
			User u3 = new User(
					"Fabio Cavaleri",
					"fabio@cavaleri.com"
			);
			User u4 = new User(
					"Davide Pirolo",
					"davide@pirolo.com"
			);
			userRepo.saveAll(List.of(u1, u2, u3, u4));

			// Create rooms
			Room r1 = new Room("Cucina");
			Room r2 = new Room("Soggiorno");
			Room r3 = new Room("Bagno");
			Room r4 = new Room("Camera");
			Room r5 = new Room("Camera Ospiti");
			roomRepo.saveAll(List.of(r1, r2, r3, r4, r5));

			// Create devices
			Device d1 = new Device("thermometer1", Category.TERMOMETRO, r1, false);
			Device d2 = new Device("thermometer2", Category.TERMOMETRO, r2, false);
			Device d3 = new Device("thermometer3", Category.TERMOMETRO, r3, false);
			Device d4 = new Device("thermometer4", Category.TERMOMETRO, r4, false);
			Device d5 = new Device("thermometer5", Category.TERMOMETRO, r5, false);
			Device d6 = new Device("movement1", Category.LUMINOSITA, r1, false);
			Device d7 = new Device("movement2", Category.LUMINOSITA, r2, false);
			Device d8 = new Device("movement3", Category.LUMINOSITA, r3, false);
			Device d9 = new Device("movement4", Category.LUMINOSITA, r4, false);
			Device d10 = new Device("movement5", Category.LUMINOSITA, r5, false);
			Device d11 = new Device("light1", Category.MOVIMENTO, r1, false);
			Device d12 = new Device("light2", Category.MOVIMENTO, r2, false);
			Device d13 = new Device("light3", Category.MOVIMENTO, r3, false);
			Device d14 = new Device("light4", Category.MOVIMENTO, r4, false);
			Device d15 = new Device("light5", Category.MOVIMENTO, r5, false);
			Device d16 = new Device("tapparella1", Category.TAPPARELLA, r1, true);
			Device d17 = new Device("tapparella2", Category.TAPPARELLA, r2, true);
			Device d18 = new Device("tapparella3", Category.TAPPARELLA, r3, true);
			Device d19 = new Device("tapparella4", Category.TAPPARELLA, r4, true);
			Device d20 = new Device("tapparella5", Category.TAPPARELLA, r5, true);
			//Device d21 = new Device("tapparella6", Category.TAPPARELLA, r5, true);
			Device d22 = new Device("condizionatore1", Category.CONDIZIONATORE, r1, true);
			Device d23 = new Device("condizionatore2", Category.CONDIZIONATORE, r2, true);
			Device d24 = new Device("condizionatore3", Category.CONDIZIONATORE, r3, true);
			Device d25 = new Device("condizionatore4", Category.CONDIZIONATORE, r4, true);
			Device d26 = new Device("condizionatore5", Category.CONDIZIONATORE, r5, true);
			Device d27 = new Device("termosifone1", Category.TERMOSIFONE, r1, true);
			Device d28 = new Device("termosifone2", Category.TERMOSIFONE, r2, true);
			Device d29 = new Device("termosifone3", Category.TERMOSIFONE, r3, true);
			Device d30 = new Device("termosifone4", Category.TERMOSIFONE, r4, true);
			Device d31 = new Device("termosifone5", Category.TERMOSIFONE, r5, true);
			Device d32 = new Device("porta1", Category.PORTA, r1, true);
			Device d33 = new Device("porta2", Category.PORTA, r2, true);
			Device d34 = new Device("porta3", Category.PORTA, r3, true);
			Device d35 = new Device("porta4", Category.PORTA, r4, true);
			Device d36 = new Device("porta5", Category.PORTA, r5, true);
			Device d37 = new Device("pulizia", Category.PULIZIA, r2, true);
			

			deviceRepo.saveAll(List.of(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31,d32,d33,d34,d35,d36,d37));

			//create rilevation

			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());


			// Rilevation rr1= new Rilevation(currentTimestamp, 10.0d, type, thermometer1);
			// Rilevation rr2= new Rilevation(currentTimestamp, 10.0d, type, thermometer2);
			// Rilevation rr3= new Rilevation(currentTimestamp, 1.0d, type, termosifone1);

		};
	}


}
