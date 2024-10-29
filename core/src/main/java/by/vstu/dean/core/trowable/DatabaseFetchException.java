package by.vstu.dean.core.trowable;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database fetch error!")
@NoArgsConstructor
public class DatabaseFetchException extends RuntimeException {

    public DatabaseFetchException(String message) {
        super(message);
    }

}
