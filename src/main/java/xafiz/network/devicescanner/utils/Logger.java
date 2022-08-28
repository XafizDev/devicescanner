package xafiz.network.devicescanner.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Logger {

    public static void info(String message) {
        send("INFO", message);
    }

    public static void error(String message) {
        send("ERROR", message);
    }

    public static void success(String message) {
        send("SUCCESS", message);
    }

    private static void send(String type, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String spaces = " ".repeat(Math.abs(type.length() - "SUCCESS".length()));
        System.out.printf("[%s] %s%s - %s\n", formatter.format(LocalTime.now()), type, spaces, message);
    }
}
