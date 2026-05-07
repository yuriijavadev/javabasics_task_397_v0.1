package com.yurii.pavlenko.app;

import com.yurii.pavlenko.weather.models.Temperature;

/**
 * Main application for the weather station simulation.
 */
public class WeatherLauncherApp {

    public static void main(String[] args) {
        // Create a temperature object based on the boiling point of water (212°F)
        // This uses our static factory method for automatic conversion.
        Temperature boiling = Temperature.fromFahrenheit(212);

        // Print the result in Celsius (Expected: 100.0)
        System.out.println(boiling.celsius());
    }
}