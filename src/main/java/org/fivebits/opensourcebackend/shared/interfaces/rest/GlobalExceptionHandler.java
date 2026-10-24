package org.fivebits.opensourcebackend.shared.interfaces.rest;

import org.fivebits.opensourcebackend.shared.interfaces.rest.resources.MessageResource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.foreign.StructLayout;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResource> handleIllegalArgumentException(IllegalArgumentException ex) {
         String localizedMessage = messageSource.getMessage(
                 ex.getMessage(),
                 null,
                 ex.getMessage(),
                 LocaleContextHolder.getLocale()

         );

         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                 .body(new MessageResource(localizedMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResource> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(errors));

            }


}
