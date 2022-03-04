package com.vikram.weather.exceptions;


/***
 * Custom exception when a downstream error occurs.
 */
public class DownstreamServerException extends RuntimeException{
    public DownstreamServerException(String message){
        super(message);
    }
}
