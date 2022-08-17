package meli.freshfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Id already exists exception.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public class IdAlreadyExistsException extends RuntimeException {
    /**
     * Instantiates a new Id already exists exception.
     *
     * @param message the message
     */
    public IdAlreadyExistsException(String message){
			super(message);
		}
	}

