package ru.scarlet.maps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.scarlet.maps.entities.CustomMap;
import ru.scarlet.maps.repository.MapRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service(value = "MapServiceImpl")
public class MapServiceImpl implements MapService {

    @Autowired
    @Qualifier("MapRepository")
    MapRepository mapRepository;

    @Override
    public CustomMap saveMap(CustomMap map) {
        return mapRepository.save(map);
    }

    @Override
    public CustomMap getMap(UUID uuid) {

        return mapRepository.findById(uuid).orElseThrow();

    }

    @Override
    public List<CustomMap> getMaps() {
        return mapRepository.findAll();
    }

    @Override
    public List<CustomMap> getMapsByOwnerId(Long id) {
        return mapRepository.findByOwner_Id(id);
    }

}
