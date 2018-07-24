package com.codecool.singletonDojo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Printer {

    private int id; // ID of the printer. Unique.
    private LocalTime busyEndTime;
    private static Printer instance = null;
    private static List<Printer> printers = new ArrayList<>();


    private Printer(int id) {
        this.id = id;
        this.busyEndTime = LocalTime.now();
    }


    // Prints out the given String
    public void print(String toPrint) {
        for (Printer print : printers) {
//            System.out.println("Start " + print.busyEndTime);
            if (print.isAvailable()) {
                System.out.println("Printer " + print.id + " is available to print: " + toPrint);
            }
            print.busyEndTime = LocalTime.now().plusSeconds(5);
//            System.out.println("End " + print.busyEndTime);
        }
        // Its not needed to actually print with a printer in this exercise
//        System.out.println("Printer " + id + " is printing.");
//        busyEndTime = LocalTime.now().plusSeconds(5);
    }

    // Returns true if the printer is ready to print now.
    public boolean isAvailable() {
        return LocalTime.now().isAfter(busyEndTime);
    }

    public static Printer getInstance(int id) {
        if (instance == null) {
            instance = new Printer(id);
            for (int i = 1; i < id + 1; i++) {
                printers.add(new Printer(i));
            }
        }
        return instance;
    }

}
