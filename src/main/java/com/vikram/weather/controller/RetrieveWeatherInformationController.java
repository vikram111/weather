package com.vikram.weather.controller;

import com.vikram.weather.application.WeatherService;
import com.vikram.weather.dto.WeatherInformation;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * This is controller class and is responsible for routing requests and follows the MVC pattern
 */
@RestController
@Validated
@Slf4j
@AllArgsConstructor
public class RetrieveWeatherInformationController {

    private WeatherService weatherService;


    /***
     *
     * @param city
     * @param countryCode
     * @param apiKey
     * @return
     */
    @GetMapping(value = "/weather/description", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Timed
    public ResponseEntity<WeatherInformation> getWeatherInformationByCityAndCountry(@RequestParam(name = "city") String city, @RequestParam(name = "country_code") String countryCode, @RequestHeader(name = "X-api-key") String apiKey){
        log.info("Received request for city {} and country {}", city,countryCode);
        return ResponseEntity.ok(weatherService.getWeatherInformation(city, countryCode));
    }
}
