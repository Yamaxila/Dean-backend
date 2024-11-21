package by.vstu.dean.core.trowable;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Mapping exception!")
@NoArgsConstructor
public class MappingException extends RuntimeException {

    public MappingException(String message) {
        super(message);
    }

}
