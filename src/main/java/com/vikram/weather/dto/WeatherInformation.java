package com.vikram.weather.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WeatherInformation {
    String description;

    @Override
    public String toString() {
        return "WeatherInformation{" +
                "description='" + description + '\'' +
                '}';
    }
}
