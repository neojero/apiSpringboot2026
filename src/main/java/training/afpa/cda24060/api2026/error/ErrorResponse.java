package training.afpa.cda24060.api2026.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> details;

    // Constructeurs, getters, setters
    public ErrorResponse(HttpStatus status, String message, List<String> details) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.details = details;
    }
}
