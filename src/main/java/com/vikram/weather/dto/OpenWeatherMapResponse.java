package com.vikram.weather.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenWeatherMapResponse {
    private List<Weather> weather;

    @Override
    public String toString() {
        return "OpenWeatherMapResponse{" +
                "weather=" + weather +
                '}';
    }
}
