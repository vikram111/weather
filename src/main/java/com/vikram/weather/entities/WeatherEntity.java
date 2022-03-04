package com.vikram.weather.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "WeatherEntity")
@NoArgsConstructor
@Getter
@Setter
public class WeatherEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "country_code")
    private String countryCode;

    public WeatherEntity(String description, String city, String countryCode) {
        this.description = description;
        this.city = city;
        this.countryCode = countryCode;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
