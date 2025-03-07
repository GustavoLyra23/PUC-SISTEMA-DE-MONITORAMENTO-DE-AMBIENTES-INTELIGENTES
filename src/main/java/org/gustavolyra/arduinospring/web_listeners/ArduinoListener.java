package org.gustavolyra.arduinospring.web_listeners;

import org.gustavolyra.arduinospring.ArduinoSpringApplication;
import org.gustavolyra.arduinospring.models.ArduinoData;
import org.gustavolyra.arduinospring.repository.DB;
import org.jfree.data.time.Millisecond;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arduino")
public class ArduinoListener {

    @PostMapping("/report")
    public void post(@RequestBody ArduinoData arduinoData) {
        DB.save(arduinoData);
        Millisecond timestamp = new Millisecond();
        ArduinoSpringApplication.temperatureSeries.addOrUpdate(timestamp, arduinoData.temperature());
        ArduinoSpringApplication.humiditySeries.addOrUpdate(timestamp, arduinoData.humidity());
        ArduinoSpringApplication.lightSeries.addOrUpdate(timestamp, arduinoData.light());
        System.out.println("Received data from Arduino: " + arduinoData);
    }

    @GetMapping
    public List<ArduinoData> get() {
        return DB.getAll();
    }
}
