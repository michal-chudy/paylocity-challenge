package com.paylocity.benefitchallenge.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

/**
 * Converts common exceptions to HTTP statuses
 */
@ControllerAdvice
public class ExceptionTranslator {

    private static final String ERR_VALIDATION = "error.validation";
    private static final String ERR_VALIDATION_FIELD = "error.validation.field";

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processIllegalArgumentException(final IllegalArgumentException ex) {
        return new ErrorDTO(ERR_VALIDATION, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return new ErrorDTO(ERR_VALIDATION_FIELD, Objects.requireNonNull(result.getFieldError()).getField());
    }
}
