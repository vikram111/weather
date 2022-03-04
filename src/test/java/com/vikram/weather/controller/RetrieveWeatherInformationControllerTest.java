package com.vikram.weather.controller;

import com.vikram.weather.dto.WeatherInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetrieveWeatherInformationControllerTest {

    @Autowired
    RetrieveWeatherInformationController retrieveWeatherInformationController;

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void contextLoads(){
        Assertions.assertNotNull(retrieveWeatherInformationController);
    }

    @Test
    void test_get_weather_success(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-api-key", "USER001");
        ResponseEntity<WeatherInformation> weatherInformationResponseEntity = restTemplate.exchange("http://localhost:"+port+"/weather/description?city=Melbourne&country_code=AU", HttpMethod.GET, new HttpEntity<>(httpHeaders), WeatherInformation.class);
        Assertions.assertEquals(HttpStatus.OK, weatherInformationResponseEntity.getStatusCode());
    }

    @Test
    void test_get_weather_fails_missing_header_key(){
        ResponseEntity<WeatherInformation> weatherInformationResponseEntity = restTemplate.getForEntity("http://localhost:"+port+"/weather/description?city=Melbourne&country_code=AU", WeatherInformation.class);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, weatherInformationResponseEntity.getStatusCode());
    }

}
