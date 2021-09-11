package hu.elte.eotvos.estike.exception.handler;

import hu.elte.eotvos.estike.dto.ErrorResponse;
import hu.elte.eotvos.estike.exception.AuthException;
import hu.elte.eotvos.estike.exception.NotFoundException;
import hu.elte.eotvos.estike.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// HTTP 400  ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException e) {
        logger.warn("Validation failed", e);

        return new ErrorResponse("validation", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warn("Bad request", e);

        return new ErrorResponse("illegalArgument", e.getMessage());
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// HTTP 401  ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthException(AuthException e) {
        logger.warn("Unauthorized access", e);

        return new ErrorResponse("unauthorized", e.getMessage());
    }
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// HTTP 404  ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        logger.warn("Requested entity not found", e);

        return new ErrorResponse("notFound", e.getMessage());
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// HTTP 422  ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.warn("Data integrity violation", e);

        return new ErrorResponse("dataIntegrity", e.getMessage());
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// HTTP 500  ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception e) {
        logger.warn("Unexpected exception occurred", e);

        return new ErrorResponse("internalServerError", e.getMessage());
    }
}
