package ru.scarlet.maps.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.maps.entities.*;
import ru.scarlet.maps.exception.CredentialCustomException;
import ru.scarlet.maps.exception.UserNotFoundException;
import ru.scarlet.maps.repository.AppUserRepository;
import ru.scarlet.maps.repository.CountryRepository;
import ru.scarlet.maps.repository.LocationRepository;
import ru.scarlet.maps.repository.MapTypeRepository;
import ru.scarlet.maps.service.MapService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MapTypeRepository mapTypeRepository;

    @GetMapping("/fillMaps")
    public void voidMethod() {
        Arrays.stream(CustomMapType.values()).forEach(maptype ->
                mapTypeRepository.save(new CustomMapTypeClass(null, maptype.name())));
        Arrays.stream(Location.values())
                .forEach(location -> {
                    var location1 = locationRepository.save(new LocationClass(null, location.name()));
                    Arrays.stream(Countries.values()).forEach(country -> countryRepository.save(
                            new Country(null, country.name(), location1)));
                });


        AppUser user1 = appUserRepository.findByUsername("adyurkov");
        AppUser user2 = appUserRepository.findByUsername("will");
        AppUser user3 = appUserRepository.findByUsername("jim");
        AppUser user4 = appUserRepository.findByUsername("arnold");


        CustomMap map1 = new CustomMap(UUID.randomUUID(), "Map#1", mapTypeRepository.findByCustomMapType(CustomMapType.ECONOMICAL.toString()), List.of(Countries.ICELAND), user1);
        CustomMap map2 = new CustomMap(UUID.randomUUID(), "Map#2", mapTypeRepository.findByCustomMapType(CustomMapType.ECONOMICAL.toString()), List.of(Countries.ICELAND, Countries.IRELAND), user2);
        CustomMap map3 = new CustomMap(UUID.randomUUID(), "Map#3", mapTypeRepository.findByCustomMapType(CustomMapType.ECONOMICAL.toString()), List.of(Countries.ICELAND, Countries.IRELAND, Countries.UKRAINE), user3);
        CustomMap map4 = new CustomMap(UUID.randomUUID(), "Map#4", mapTypeRepository.findByCustomMapType(CustomMapType.ECONOMICAL.toString()), List.of(Countries.ICELAND, Countries.IRELAND, Countries.UKRAINE, Countries.RUSSIA), user4);
        CustomMap map5 = new CustomMap(UUID.randomUUID(), "Map#5", mapTypeRepository.findByCustomMapType(CustomMapType.POLITICAL.toString()), List.of(Countries.ICELAND, Countries.IRELAND, Countries.UKRAINE, Countries.RUSSIA, Countries.UNITEDSTATES), user2);

        mapService.saveMap(map1);
        mapService.saveMap(map2);
        mapService.saveMap(map3);
        mapService.saveMap(map4);
        mapService.saveMap(map5);

        user1.getMaps().add(map1);
        user2.getMaps().add(map2);
        user3.getMaps().add(map3);
        user4.getMaps().add(map4);
        user2.getMaps().add(map5);


        appUserRepository.save(user1);
        appUserRepository.save(user2);
        appUserRepository.save(user3);
        appUserRepository.save(user4);

    }

    @GetMapping("/api/v1/maps")
    public List<CustomMap> getMaps() {
        return mapService.getMaps();
    }

    @GetMapping("/api/v1/maps/{id}")
    public CustomMap getMap(@PathVariable UUID id) {
        return mapService.getMap(id);
    }

    @GetMapping("/api/v1/maps/owner/{id}")
    public List<CustomMap> getMapsByOwnerId(@PathVariable Long id, HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String refresh_token = authorizationHeader.substring("Bearer ".length());

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT jwt = verifier.verify(refresh_token);

        AppUser user = appUserRepository.findByUsername(jwt.getSubject());
        if (user == null) {
            //fixme: throw exception
            throw new UserNotFoundException("User not found");
        }
        if (!user.getId().equals(id)) {
            throw new CredentialCustomException("Wrong user");
        }
        return mapService.getMapsByOwnerId(id);
    }

    @GetMapping("/api/v1/countries")
    public List<Countries> getCountries() {
        return mapService.getCountries();
    }

}
