package com.vikram.weather.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

/***
 * Standard error object returned by api when a caught exception is thrown of type
 * NoSuchProductException or NoSuchProductOption exception
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;

}