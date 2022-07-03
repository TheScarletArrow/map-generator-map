package ru.scarlet.maps.service;


import ru.scarlet.maps.entities.Countries;
import ru.scarlet.maps.entities.Country;
import ru.scarlet.maps.entities.CustomMap;

import java.util.List;
import java.util.UUID;

public interface MapService {
    CustomMap saveMap(CustomMap map);
    CustomMap getMap(UUID uuid);
    List<CustomMap> getMaps();

    List<CustomMap> getMapsByOwnerId(Long id);

    List<Countries> getCountries();
}
