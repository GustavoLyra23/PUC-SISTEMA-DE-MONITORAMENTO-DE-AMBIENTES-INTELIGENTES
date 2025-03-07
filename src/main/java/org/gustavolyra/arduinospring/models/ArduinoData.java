package org.gustavolyra.arduinospring.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.logging.Logger;

/**
 * Data class to represent the data sent by the Arduino
 *
 * @param temperature
 * @param humidity
 * @param light
 */
public record ArduinoData(@JsonAlias(value = "Temperatura") Double temperature, @JsonAlias(value = "Umidade do Solo") Double humidity,
                          @JsonAlias(value = "Luminosidade") Double light) {
    private static final Logger logger = Logger.getLogger(ArduinoData.class.getName());

    public ArduinoData {
        if (temperature < -40 || temperature > 80) {
            logger.log(java.util.logging.Level.WARNING, "Temperature out of range: " + temperature);
        }
        if (humidity < 0 || humidity > 100) {
            logger.log(java.util.logging.Level.WARNING, "Humidity out of range: " + humidity);
        }
        if (light < 0 || light > 1023) {
            logger.log(java.util.logging.Level.WARNING, "Light out of range: " + light);
        }
    }
}
