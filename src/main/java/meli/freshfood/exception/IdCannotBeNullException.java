package meli.freshfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Id cannot be null exception.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IdCannotBeNullException extends RuntimeException {
    /**
     * Instantiates a new Id cannot be null exception.
     *
     * @param message the message
     */
    public IdCannotBeNullException(String message){
            super(message);
        }
}
