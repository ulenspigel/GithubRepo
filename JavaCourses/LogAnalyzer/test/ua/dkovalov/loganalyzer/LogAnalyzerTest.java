package ua.dkovalov.loganalyzer;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class LogAnalyzerTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss xxxx");

    @Test
    public void testLogAnalyzer() {
        ArrayList<LogToken> list = new ArrayList<>();
        list =  LogAnalyzer.filterLogEntries("test-resources/apache.log", LocalDateTime.parse("07/Mar/2004:17:00:00 -0800", formatter),
                LocalDateTime.parse("07/Mar/2004:17:30:00 -0800", formatter));
        assertEquals(list.size(), 15);
        assertEquals(list.get(0).getMethod(), LogToken.HttpMethod.GET);
        assertEquals(list.get(1).getMethod(), LogToken.HttpMethod.POST);
        list =  LogAnalyzer.filterLogEntries("test-resources/apache.log", LocalDateTime.parse("08/Mar/2004:00:00:00 -0800", formatter),
                LocalDateTime.parse("08/Mar/2004:23:59:59 -0800", formatter));
        assertEquals(list.size(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLogAnalyzerException() {
        ArrayList<LogToken> list = LogAnalyzer.filterLogEntries("test-resources/apache.log", LocalDateTime.parse("08/Mar/2004:17:00:00 -0800", formatter),
                LocalDateTime.parse("07/Mar/2004:17:30:00 -0800", formatter));
    }
}
