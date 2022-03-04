package com.vikram.weather.repository;

import com.vikram.weather.dto.OpenWeatherMapResponse;
import com.vikram.weather.exceptions.DownstreamServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/***
 * This repository class is resposible for getting weather information from openWeathermap api
 */
@Component
public class WeatherOpenApiRepository {

    @Value("${downstream.service.weatherOpenApi.url}")
    private String endpoint;

    @Value("${downstream.service.weatherOpenApi.appKey}")
    private String appKey;

    @Autowired
    private RestTemplate restTemplate;

    /***
     *
     * @param city
     * @param countryCode
     * @return
     * @throws DownstreamServerException
     */
    public OpenWeatherMapResponse retrieveWeatherInformationForCityAndCountry(String city, String countryCode) throws DownstreamServerException {
        if(city == null || countryCode == null){
            throw new IllegalArgumentException("city or country cant be null");
        }
        Map<String, String> queryParams= new HashMap<>();
        queryParams.put("city", city);
        queryParams.put("country", countryCode);
        queryParams.put("appid", appKey);
        ResponseEntity<OpenWeatherMapResponse> openWeatherMapResponseResponseEntity = restTemplate.getForEntity(endpoint, OpenWeatherMapResponse.class, queryParams);
        if(openWeatherMapResponseResponseEntity.getStatusCode().is2xxSuccessful()){
            return openWeatherMapResponseResponseEntity.getBody();
        }else{
            throw new DownstreamServerException(openWeatherMapResponseResponseEntity.getStatusCode().getReasonPhrase());
        }

    }




}
