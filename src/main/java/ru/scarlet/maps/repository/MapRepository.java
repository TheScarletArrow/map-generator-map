package ru.scarlet.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.maps.entities.CustomMap;

import java.util.List;
import java.util.UUID;

@Repository(value = "MapRepository")
public interface MapRepository extends JpaRepository<CustomMap, UUID> {
    List<CustomMap> findByOwner_Id(Long id);



}
