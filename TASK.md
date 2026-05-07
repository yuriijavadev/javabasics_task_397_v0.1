### You're developing a weather station system that receives temperature data in various formats. Although the data is primarily stored in degrees Celsius, sometimes it arrives in Fahrenheit and needs to be converted.

#### - Create a record class that will hold a single value—the temperature in degrees Celsius.

#### - Then add a special helper method to this class that will create a temperature object by accepting a Fahrenheit value and automatically converting it to Celsius using a known formula.

#### - In the main program, use this method to create a temperature object corresponding to the boiling point of water (212°F), and then display the resulting value in Celsius.

```java
public class WeatherLauncherApp {
    public static void main(String[] args) {
        // Create a temperature object based on a Fahrenheit value (212°F—the boiling point of water)
        Temperature boiling = Temperature.fromFahrenheit(212);
        
        // Print the value in degrees Celsius
        System.out.println(boiling.celsius());
    }
}
```
