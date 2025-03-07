package org.gustavolyra.arduinospring.repository;

import org.gustavolyra.arduinospring.models.ArduinoData;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple in-memory database to store the data sent by the Arduino
 */
public class DB {

    private static final ConcurrentHashMap<Integer, ArduinoData> db = new ConcurrentHashMap<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void save(ArduinoData data) {
        db.put(counter.incrementAndGet(), data);
    }

    public static ArduinoData get(Integer key) {
        return db.get(key);
    }

    public static List<ArduinoData> getAll() {
        return List.copyOf(db.values());
    }

    public static void clear() {
        db.clear();
    }
}
