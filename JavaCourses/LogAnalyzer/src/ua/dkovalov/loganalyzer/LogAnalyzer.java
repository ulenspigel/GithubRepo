package ua.dkovalov.loganalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LogAnalyzer {
    public static ArrayList<LogToken> filterLogEntries(String path, LocalDateTime timeFrom, LocalDateTime timeTo) {
        if (timeFrom.isAfter(timeTo)) {
            throw new IllegalArgumentException("Value of timeFrom parameter has to be less then the timeTo");
        }

        ArrayList<LogToken> filteredEntries = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss xxxx");
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String entry;
            while ((entry = reader.readLine()) != null) {
                String[] tokens = entry.split(" ");
                LocalDateTime entryTime = LocalDateTime.parse(tokens[3].replace("[", "") + " " + tokens[4].replace("]", ""), formatter);
                if (entryTime.isAfter(timeFrom) && entryTime.isBefore(timeTo)) {
                    LogToken.HttpMethod httpMethod = LogToken.HttpMethod.valueOf(tokens[5].replace("\"", ""));
                    String message = tokens[6];
                    LogToken logToken = new LogToken(entryTime, httpMethod, message);
                    filteredEntries.add(logToken);
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
        return filteredEntries;
    }
}
