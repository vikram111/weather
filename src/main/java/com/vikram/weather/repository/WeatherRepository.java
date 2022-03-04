package com.vikram.weather.repository;

import com.vikram.weather.entities.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/***
 * This class is responsible for database interactions with h2 database.
 */
@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, UUID> {

    @Query("Select w From WeatherEntity w where w.city = ?1 and w.countryCode = ?2")
    Optional<WeatherEntity> findByCityAndCountryCode(String city, String countryCode);
}
