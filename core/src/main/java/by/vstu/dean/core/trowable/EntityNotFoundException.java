package by.vstu.dean.core.trowable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found in database!")
public class EntityNotFoundException extends RuntimeException {
}
