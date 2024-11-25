package br.com.grzsoftware.monolithapi.common.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ApiErrorMessage {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private List<String> errors;

    public ApiErrorMessage(HttpStatus status, List<String> errors) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

    public ApiErrorMessage(HttpStatus status, String error) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = Collections.singletonList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
