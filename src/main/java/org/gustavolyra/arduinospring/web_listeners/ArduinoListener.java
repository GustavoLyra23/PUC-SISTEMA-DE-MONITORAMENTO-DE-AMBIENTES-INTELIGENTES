package org.gustavolyra.arduinospring.web_listeners;

import org.gustavolyra.arduinospring.models.ArduinoData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arduino")
public class ArduinoListener {

    @PostMapping("/report")
    public void post(@RequestBody ArduinoData arduinoData) {
        System.out.println("ArduinoListener.post");
    }
}
