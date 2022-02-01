package com.smarthome.SmartHome.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User pippo = new User(
                    "pippo",
                    "pippo@demo.com"
            );
            User pluto = new User(
                    "pluto",
                    "pluto@demo.com"
            );
            repository.saveAll(List.of(pippo, pluto));
        };
    }

}
