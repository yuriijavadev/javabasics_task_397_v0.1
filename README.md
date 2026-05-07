# Weather Station: Factory Methods in Records (JavaBasics_Task_397_V0.1)

## 📖 Description
Weather monitoring systems often encounter heterogeneous data sources. This project demonstrates the use of **Static Factory Methods** within Java Records to handle data conversion. By implementing the `fromFahrenheit()` method, the `Temperature` record gains the ability to encapsulate complex conversion logic while maintaining its primary focus as a Celsius data carrier. This approach improves API clarity and ensures that conversion formulas are centralized within the relevant data model.

## 📋 Requirements Compliance
- **Data Encapsulation**: Created a `Temperature` record to store values in degrees Celsius.
- **Factory Logic**: Implemented a static method for Fahrenheit conversion using the standard formula.
- **Precise Calculation**: Handled temperature conversion to demonstrate the relationship between different scales.

## 🚀 Architectural Stack
- Java 16+ (Records, Static Methods, Mathematical Logic)

## 🏗️ Implementation Details
- **Temperature**: A record with a specialized factory method for scale conversion.
- **WeatherLauncherApp**: The entry point for testing the boiling point conversion (212°F to 100°C).

## 📋 Expected result
```text
100.0
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_397/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── WeatherLauncherApp.java
    │                 └── weather/
    │                     └── models/
    │                         └── Temperature.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.weather.models.Temperature;

public class WeatherLauncherApp {

    public static void main(String[] args) {

        Temperature boiling = Temperature.fromFahrenheit(212);

        System.out.println(boiling.celsius());
    }
}
```
```java
package com.yurii.pavlenko.weather.models;

public record Temperature(double celsius) {

    public static Temperature fromFahrenheit(double fahrenheit) {
        double celsiusValue = (fahrenheit - 32) * 5 / 9;
        return new Temperature(celsiusValue);
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
