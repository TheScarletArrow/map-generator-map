package ru.scarlet.maps.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.maps.entities.AppUser;
import ru.scarlet.maps.entities.Countries;
import ru.scarlet.maps.entities.CustomMap;
import ru.scarlet.maps.exception.CredentialCustomException;
import ru.scarlet.maps.exception.UserNotFoundException;
import ru.scarlet.maps.repository.AppUserRepository;
import ru.scarlet.maps.service.MapService;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    @Autowired
    private AppUserRepository appUserRepository;

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
        if (user==null) {
            //fixme: throw exception
            throw new UserNotFoundException("User not found");
        }
        if (!user.getId().equals(id)){
            throw new CredentialCustomException("Wrong user");
        }
        return mapService.getMapsByOwnerId(id);
    }

    @GetMapping("/api/v1/countries")
    public List<Countries> getCountries() {
        return mapService.getCountries();
    }

}
