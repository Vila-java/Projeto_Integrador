package meli.freshfood.exception.handler;


import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.ExceptionDetails;
import meli.freshfood.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionGenericsHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDetails> handlerNotFoundException(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
						.title("Not found")
						.status(HttpStatus.NOT_FOUND.value())
						.message(ex.getMessage())
						.timestamp(LocalDateTime.now())
						.build(),
				HttpStatus.NOT_FOUND
		);
	}
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionDetails> handlerBadRequestException(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
						.title("Bad Request")
						.status(HttpStatus.BAD_REQUEST.value())
						.message(ex.getMessage())
						.timestamp(LocalDateTime.now())
						.build(),
				HttpStatus.BAD_REQUEST
		);
	}

	@ExceptionHandler(BadRequestException.class)
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

	@ExceptionHandler(BadRequestException.class)
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
