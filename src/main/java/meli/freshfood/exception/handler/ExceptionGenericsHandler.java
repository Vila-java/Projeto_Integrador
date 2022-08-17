package meli.freshfood.exception.handler;


import meli.freshfood.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * The type Exception generics handler.
 */
@ControllerAdvice
public class ExceptionGenericsHandler {
    /**
     * Handler not found exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDetails> handlerNotFoundException(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
						.title("Not found!")
						.status(HttpStatus.NOT_FOUND.value())
						.message(ex.getMessage())
						.timestamp(LocalDateTime.now())
						.build(),
				HttpStatus.NOT_FOUND
		);
	}

    /**
     * Handler bad request exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionDetails> handlerBadRequestException(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
						.title("Bad request!")
						.status(HttpStatus.BAD_REQUEST.value())
						.message(ex.getMessage())
						.timestamp(LocalDateTime.now())
						.build(),
				HttpStatus.BAD_REQUEST
		);
	}

    /**
     * Handler id cannot be null response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(IdCannotBeNullException.class)
	public ResponseEntity<ExceptionDetails> handlerIdCannotBeNull(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
						.title("Id cannot be null!")
						.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message(ex.getMessage())
						.timestamp(LocalDateTime.now())
						.build(),
				HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

    /**
     * Handler id already exists response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(IdAlreadyExistsException.class)
	public ResponseEntity<ExceptionDetails> handlerIdAlreadyExists(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
						.title("Id already exists!")
						.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message(ex.getMessage())
						.timestamp(LocalDateTime.now())
						.build(),
				HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}
