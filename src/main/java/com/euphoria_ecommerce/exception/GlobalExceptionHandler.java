package com.euphoria_ecommerce.exception;

import com.euphoria_ecommerce.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseMessage> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ResponseMessage> handleUserNotFoundException(UsernameNotFoundException exception) {
//        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {

        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleRatingNotFoundException(RatingNotFoundException exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleOrderNotFoundException(OrderNotFoundException exception) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AddToCartNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleAddToCartNotFoundException(AddToCartNotFoundException exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ViewedProductNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleViewedProductNotFoundException(ViewedProductNotFoundException exception) {
        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(WishlistNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleRatingNotFoundException(WishlistNotFoundException exception) {

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResponseMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseMessage> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResponseMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResponseMessage("An error occurred: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResponseMessage("Invalid argument: " + ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessage> handleAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ResponseMessage("Access denied: " + ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
