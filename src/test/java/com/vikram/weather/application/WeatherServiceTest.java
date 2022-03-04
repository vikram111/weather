package com.vikram.weather.application;

import com.vikram.weather.dto.WeatherInformation;
import com.vikram.weather.entities.WeatherEntity;
import com.vikram.weather.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void test_weather_service_returns_weather_information_from_the_database(){
        WeatherEntity weatherEntity = new WeatherEntity("snowy", "Bright","AU");

        WeatherEntity savedEntity = weatherRepository.save(weatherEntity);
        System.out.println("Saved is => "+ savedEntity);

        WeatherInformation weatherInformation = weatherService.getWeatherInformation("Bright", "AU");
        Assertions.assertEquals("snowy", weatherInformation.getDescription());
    }


    @Test
    public void test_weather_service_throws_exception_when_city_country_are_null(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            weatherService.getWeatherInformation(null,null);
        });
    }
}
