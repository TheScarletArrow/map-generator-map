package ru.scarlet.maps;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.scarlet.maps.entities.*;
import ru.scarlet.maps.repository.AppUserRepository;
import ru.scarlet.maps.repository.CountryRepository;
import ru.scarlet.maps.repository.LocationRepository;
import ru.scarlet.maps.service.MapService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class MapsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapsApplication.class, args);
    }


}

