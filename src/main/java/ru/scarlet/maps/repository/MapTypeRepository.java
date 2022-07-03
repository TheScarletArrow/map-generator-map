package ru.scarlet.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.maps.entities.CustomMapType;
import ru.scarlet.maps.entities.CustomMapTypeClass;

@Repository
public interface MapTypeRepository extends JpaRepository<CustomMapTypeClass, Long> {
    CustomMapTypeClass findByCustomMapType(String customMapType);


}
