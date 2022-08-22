package by.rudenko.activityworker.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WeatherServiceException extends RuntimeException{
    private final HttpStatus status;

    public WeatherServiceException(HttpStatus status) {
        this.status = status;
    }
    public WeatherServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public WeatherServiceException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public WeatherServiceException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
