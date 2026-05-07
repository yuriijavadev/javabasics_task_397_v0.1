package com.yurii.pavlenko.weather.models;

/**
 * A record representing temperature in degrees Celsius.
 * Includes a factory method for Fahrenheit conversion.
 */
public record Temperature(double celsius) {

    /**
     * Static factory method to create a Temperature instance from Fahrenheit.
     * Uses the formula: (F - 32) * 5/9
     * * @param Fahrenheit The temperature in degrees Fahrenheit.
     * @return A new Temperature record in Celsius.
     */
    public static Temperature fromFahrenheit(double fahrenheit) {
        double celsiusValue = (fahrenheit - 32) * 5 / 9;
        return new Temperature(celsiusValue);
    }
}