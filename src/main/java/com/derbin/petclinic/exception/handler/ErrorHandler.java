package com.derbin.petclinic.exception.handler;

import com.derbin.petclinic.exception.ApplicationClientException;
import com.derbin.petclinic.exception.ApplicationServerException;
import com.derbin.petclinic.exception.PetNotFoundException;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;
import static org.slf4j.LoggerFactory.getLogger;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = getLogger(ErrorHandler.class);

    @ExceptionHandler(ApplicationClientException.class)
    protected ResponseEntity<?> handleClientException(final ApplicationClientException e) {
        logger.error("Client exception: ", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse((e)));
    }

    @ExceptionHandler(PetNotFoundException.class)
    protected ResponseEntity<?> petNotFoundException(final PetNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse((e)));
    }

    @ExceptionHandler(ApplicationServerException.class)
    protected ResponseEntity<?> handleServiceException(final ApplicationServerException exception) {
        logger.error("Server Exception: ", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Unknown exception"));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                .collect(toMap(
                        error -> ((FieldError) error).getField(),
                        error -> Optional.ofNullable(error.getDefaultMessage()).orElse(""))
                );
        return handleExceptionInternal(ex, errors, headers, status, request);
    }
}