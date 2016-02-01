package ua.dkovalov.loganalyzer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LogToken {
    enum HttpMethod {GET, POST};

    private LocalDateTime time;
    private HttpMethod method;
    private String message;

    public LogToken(LocalDateTime time, HttpMethod method, String message) {
        this.time = time;
        this.method = method;
        this.message = message;
    }

    public LogToken(String time, String method, String message) {
        this.time = LocalDateTime.parse(time);
        this.method = method.equals("GET") ? HttpMethod.GET : HttpMethod.POST;
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " " + (method == HttpMethod.GET ? "GET" : "POST") +
                " " + message;
    }
}
