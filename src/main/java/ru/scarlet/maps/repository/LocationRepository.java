package ru.scarlet.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.maps.entities.LocationClass;

@Repository
public interface LocationRepository extends JpaRepository<LocationClass, Long> {

}
