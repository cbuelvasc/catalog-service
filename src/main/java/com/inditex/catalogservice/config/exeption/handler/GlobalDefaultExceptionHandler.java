package com.inditex.catalogservice.config.exeption.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.inditex.catalogservice.commons.exeption.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Slf4j
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler {

    private static final String ERROR_EXCEPTION_TEMPLATE = "Error [{}]";
    private final ExceptionMessage exceptionMessage;

    public GlobalDefaultExceptionHandler(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Handle general uncaught exceptions
     *
     * @param ex - Exception
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ExceptionResponse defaultErrorHandler(Exception ex) {
        log.error("An unhandled exception has occurred", ex);

        return new ExceptionResponse(ExceptionEnum.SERVER_ERROR.getValue(), "");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ExceptionResponse handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        log.error(ERROR_EXCEPTION_TEMPLATE, ex.getMessage());

        String message = Match(ex.getCause()).of(
                Case($(instanceOf(InvalidFormatException.class)), cause -> {
                    String path = cause.getPath().stream().map(Reference::getFieldName).collect(Collectors.joining("."));
                    String error = exceptionMessage.getMessage(ExceptionEnum.FIELD_FORMAT_INVALID.getValue());
                    return String.format(error, path);
                }),
                Case($(instanceOf(JsonMappingException.class)), cause -> {
                    String error = exceptionMessage.getMessage(ExceptionEnum.JSON_FORMAT_INVALID.getValue());
                    return String.format(error, cause.getLocation().getLineNr(), cause.getLocation().getColumnNr());
                }),
                Case($(), exceptionMessage.getMessage(ExceptionEnum.HTTP_MESSAGE_NOT_READABLE.getValue()))
        );

        return new ExceptionResponse(ExceptionEnum.REQUEST_INPUT_ERROR.getValue(), null, message);
    }

    /**
     * Handle ServletRequestBindingException
     *
     * @param ex - Exception
     */
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleServletRequestBindingException(ServletRequestBindingException ex) {
        log.error(ERROR_EXCEPTION_TEMPLATE, ex.getMessage());

        return new ExceptionResponse(ExceptionEnum.REQUEST_INPUT_ERROR.getValue(), null, ex.getMessage());
    }

    /**
     * Handle HttpMediaTypeNotSupportedExceptions
     *
     * @param ex - Exception
     */
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ExceptionResponse handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException ex) {
        log.error(ERROR_EXCEPTION_TEMPLATE, ex.getMessage());

        String unsupported = "Unsupported content type: " + ex.getContentType();

        return new ExceptionResponse(ExceptionEnum.REQUEST_INPUT_ERROR.getValue(), null, unsupported);
    }

    /**
     * Handle ConstraintViolationExceptions
     *
     * @param ce - Exception
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ExceptionResponse constraintViolationHandler(ConstraintViolationException ce) {
        log.error(ERROR_EXCEPTION_TEMPLATE, ce.getMessage());
        return new ExceptionResponse(ExceptionEnum.REQUEST_INPUT_ERROR.getValue(), ce.getMessage());
    }

    /**
     * Handle MethodArgumentNotValidException
     *
     * @param ex - Exception
     */
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.error(ERROR_EXCEPTION_TEMPLATE, ex.getMessage());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }

        return new ExceptionResponse(ExceptionEnum.REQUEST_INPUT_ERROR.getValue(), null, errors);

    }

    /**
     * Handle InditexExceptions
     *
     * @param inditexException The Inditex exception {@link InditexException}
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {InditexException.class})
    public ExceptionResponse exceptionHandler(InditexException inditexException) {
        log.error(ERROR_EXCEPTION_TEMPLATE, inditexException.getMessage());

        String message = exceptionMessage.getMessage(inditexException.getErrorCode());
        return new ExceptionResponse(inditexException.getErrorCode(), null, message);
    }

    /**
     * Handle NotFoundException
     *
     * @param notFoundException The Inditex exception {@link NotFoundException}
     */
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundExceptionHandler(NotFoundException notFoundException) {
        log.error(ERROR_EXCEPTION_TEMPLATE, notFoundException.getMessage());

        String message = exceptionMessage.getMessage(notFoundException.getErrorCode());
        return new ExceptionResponse(notFoundException.getErrorCode(), null, message);
    }

    /**
     * Handle UnProcessableEntityException
     *
     * @param unProcessableEntityException The Inditex exception {@link UnProcessableEntityException}
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {UnProcessableEntityException.class})
    public ExceptionResponse unProcessableEntityExceptionHandler(UnProcessableEntityException unProcessableEntityException) {
        log.warn(ERROR_EXCEPTION_TEMPLATE, unProcessableEntityException.getMessage());

        String message = exceptionMessage.getMessage(unProcessableEntityException.getErrorCode());
        return new ExceptionResponse(unProcessableEntityException.getErrorCode(), null, message);
    }

}
