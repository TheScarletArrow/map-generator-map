package ru.scarlet.maps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.scarlet.maps.entities.CustomMap;
import ru.scarlet.maps.service.MapService;

import java.util.List;
import java.util.UUID;

@RestController("")
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping("/api/v1/maps")
    public List<CustomMap> getMaps() {
        return mapService.getMaps();
    }

    @GetMapping("/api/v1/maps/{id}")
    public CustomMap getMap(@PathVariable UUID id) {
        return mapService.getMap(id);
    }

    @GetMapping("/api/v1/maps/owner/{id}")
    public List<CustomMap> getMapsByOwnerId(@PathVariable Long id) {
        return mapService.getMapsByOwnerId(id);
    }

}
