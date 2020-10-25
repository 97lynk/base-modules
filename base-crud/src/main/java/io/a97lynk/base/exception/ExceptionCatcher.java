package io.a97lynk.base.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@EnableWebMvc
public class ExceptionCatcher extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataException.class)
    public ErrorInfo handleDataException(DataException ex) {
        return ErrorInfo.builder()
                .code(ErrorConstant.DATA_NOT_EXIST)
                .message(ex.getMessage())
                .build();
    }

    // 404
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorInfo.builder()
                        .code(ErrorConstant.HANDLE_NOT_FOUND)
                        .message(ex.getMessage())
                        .build());
    }
}
