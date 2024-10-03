package by.vstu.dean.core.trowable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database fetch error!")
public class DatabaseFetchException extends RuntimeException {
}
