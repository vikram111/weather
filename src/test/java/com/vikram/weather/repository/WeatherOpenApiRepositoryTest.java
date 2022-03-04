package com.vikram.weather.repository;

import com.vikram.weather.dto.OpenWeatherMapResponse;
import com.vikram.weather.dto.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherOpenApiRepositoryTest {

    @Autowired
    WeatherOpenApiRepository weatherOpenApiRepository;

    @Test
    public void test_open_weather_map_api_returns_success(){
        OpenWeatherMapResponse openWeatherMapResponse = weatherOpenApiRepository.retrieveWeatherInformationForCityAndCountry("Melbourne", "AU");
        Weather weather = openWeatherMapResponse.getWeather().get(0);
        Assertions.assertEquals(800,weather.getId());
    }

    @Test
    public void test_open_weather_api_repository_throws_exception_when_city_or_country_is_null(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            weatherOpenApiRepository.retrieveWeatherInformationForCityAndCountry(null,"AU");
        });
    }

}
