package meli.freshfood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IdCannotBeNullException extends RuntimeException {
        public IdCannotBeNullException(String message){
            super(message);
        }
}
