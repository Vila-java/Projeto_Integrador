package meli.freshfood.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionDetails {

    private String title;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
