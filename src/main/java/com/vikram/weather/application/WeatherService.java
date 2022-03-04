package com.vikram.weather.application;

import com.vikram.weather.dto.OpenWeatherMapResponse;
import com.vikram.weather.dto.WeatherInformation;
import com.vikram.weather.entities.WeatherEntity;
import com.vikram.weather.exceptions.DownstreamServerException;
import com.vikram.weather.repository.WeatherOpenApiRepository;
import com.vikram.weather.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 * This is the service class responsible for managing the business logic for the application.
 */
@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {

    private WeatherRepository weatherRepository;
    private WeatherOpenApiRepository weatherOpenApiRepository;

    /***
     *
     * @param city
     * @param country
     * @return
     */
    public WeatherInformation getWeatherInformation(String city, String country){
        if(city == null || country == null){
            throw new IllegalArgumentException("Either city or country should be specified");
        }

        return weatherRepository.findByCityAndCountryCode(city, country)
                .map(weatherEntity -> {
                    WeatherInformation weatherInformation = WeatherInformation.builder()
                            .description(weatherEntity.getDescription()).build();
                    log.info("Weather info received is => {}",weatherInformation);
                    return weatherInformation;
                })
                .orElseGet(() -> fetchFromOpenWeatherMapAndStoreInDatabase(city,country));
    }

    /***
     *
     * @param weatherEntity
     * @return
     */
    private WeatherInformation mapFromWeatherEntity(WeatherEntity weatherEntity){
        return WeatherInformation
                .builder()
                .description(weatherEntity.getDescription())
                .build();
    }

    /***
     *
     * @param city
     * @param countryCode
     * @return
     * @throws DownstreamServerException
     */
    private WeatherInformation fetchFromOpenWeatherMapAndStoreInDatabase(String city, String countryCode) throws DownstreamServerException {
        OpenWeatherMapResponse openWeatherMapResponse = weatherOpenApiRepository.retrieveWeatherInformationForCityAndCountry(city, countryCode);
        return openWeatherMapResponse.getWeather()
                .stream()
                .map(weather -> {
                    WeatherEntity weatherEntity = new WeatherEntity(weather.getDescription(), city, countryCode);
                    log.info("Inserting record => {}",weatherEntity);
                    return weatherRepository.save(weatherEntity);
                })
                .map(this::mapFromWeatherEntity)
                .findFirst()
                .orElseThrow(() -> new DownstreamServerException("There was a downstream error"));
    }
}
