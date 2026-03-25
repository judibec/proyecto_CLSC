package com.financialLab.financedevapp.exception;

import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.financialLab.financedevapp.exception.ErrorCode.NOT_FOUND;


@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FinancialAppException.class)
    public  ResponseEntity<ResponseDTO<String>> handleClientAppException(FinancialAppException e) {
        if (e.isLogError()) {
            logger.error(e.getMessage(), e);
            logger.error("Unhandled Exception", e);
        }
        return ResponseEntity.status(e.getHttpStatus()).body(ResponseDTO.ofError(e.getErrorCode(), e.getApiResultMessage()));
    }

    private String getRootCause(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause != rootCause.getCause()) {
            rootCause = rootCause.getCause();
        }
        return rootCause.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Unhandled Exception", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.ofError(ErrorCode.INTERNAL_SERVER_ERROR, "An error occurred " + ex.getMessage()+ "| Cause: " + getRootCause(ex)));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        logger.error("Unhandled Exception", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.ofError(ErrorCode.INTERNAL_SERVER_ERROR, "Null value encountered: " + ex.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDTO<String>> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        logger.error("Unhandled Exception", e);
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ResponseDTO.ofError(ErrorCode.METHOD_NOT_ALLOWED, "HTTP method not supported: " + e.getMethod()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseDTO<String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error("Unhandled Exception", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDTO.ofError(NOT_FOUND,"Endpoint not found: " + ex.getRequestURL()));
    }
}
