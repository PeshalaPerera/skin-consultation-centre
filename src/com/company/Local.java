package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Local {
    public static void main(String[] args) {
        String x = "10-01-2023 12:02:19";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(x, formatter);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(time + " " + now);
    }
}
