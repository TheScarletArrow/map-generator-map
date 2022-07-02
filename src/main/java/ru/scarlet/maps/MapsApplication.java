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

    @Bean
    CommandLineRunner run(
            MapService mapService,
            AppUserRepository appUserRepository,
            CountryRepository countryRepository,
            LocationRepository locationRepository) {
        return args -> {
            Arrays.stream(Location.values())
                    .forEach(location -> {
                        var location1 = locationRepository.save(new LocationClass(null, location.name()));
//                        int random = ThreadLocalRandom.current().nextInt(0, Location.values().length);
                        Arrays.stream(Countries.values()).forEach(country -> countryRepository.save(
                                new Country(null, country.name(), location1)));
                    });

            AppUser user1 = appUserRepository.findByUsername("adyurkov");
            AppUser user2 = appUserRepository.findByUsername("will");
            AppUser user3 = appUserRepository.findByUsername("jim");
            AppUser user4 = appUserRepository.findByUsername("arnold");



            CustomMap map1 = new CustomMap(UUID.randomUUID(), "Map#1", CustomMapType.ECONOMICAL, List.of(Countries.ICELAND), user1);
            CustomMap map2 = new CustomMap(UUID.randomUUID(), "Map#2", CustomMapType.ECONOMICAL, List.of(Countries.ICELAND, Countries.IRELAND), user2);
            CustomMap map3 = new CustomMap(UUID.randomUUID(), "Map#3", CustomMapType.ECONOMICAL, List.of(Countries.ICELAND, Countries.IRELAND, Countries.UKRAINE), user3);
            CustomMap map4 = new CustomMap(UUID.randomUUID(), "Map#4", CustomMapType.ECONOMICAL, List.of(Countries.ICELAND, Countries.IRELAND, Countries.UKRAINE, Countries.RUSSIA), user4);
            CustomMap map5 = new CustomMap(UUID.randomUUID(), "Map#5", CustomMapType.POLITICAL, List.of(Countries.ICELAND, Countries.IRELAND, Countries.UKRAINE, Countries.RUSSIA, Countries.UNITEDSTATES), user2);

            mapService.saveMap(map1);
            mapService.saveMap(map2);
            mapService.saveMap(map3);
            mapService.saveMap(map4);
            mapService.saveMap(map5);


        };
    }
}

