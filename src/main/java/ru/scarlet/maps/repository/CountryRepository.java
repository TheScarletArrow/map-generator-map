package ru.scarlet.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scarlet.maps.entities.Country;

@Repository(value = "CountryRepository")
public interface CountryRepository extends JpaRepository<Country, Long> {
}
