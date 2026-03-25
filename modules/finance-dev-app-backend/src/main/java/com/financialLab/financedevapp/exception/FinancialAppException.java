package com.financialLab.financedevapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FinancialAppException extends RuntimeException{

    public static String NOT_FOUND = "The client wasn't found";
    public static String NOT_AUTHORIZED = "you don't have access";

    protected final ErrorCode errorCode;
    protected final String apiResultMessage;
    protected final HttpStatus httpStatus;
    protected final boolean logError;

    public FinancialAppException(String apiResultMessage, HttpStatus httpStatus, ErrorCode errorCode, boolean logError) {
        super(apiResultMessage);
        this.errorCode = errorCode;
        this.apiResultMessage = apiResultMessage;
        this.httpStatus = httpStatus;
        this.logError = logError;
    }

    public FinancialAppException(String internalMessage, String apiResultMessage, HttpStatus httpStatus, ErrorCode errorCode, boolean logError) {
        super(internalMessage);
        this.errorCode = errorCode;
        this.apiResultMessage = apiResultMessage;
        this.httpStatus = httpStatus;
        this.logError = logError;
    }

    public FinancialAppException(String apiResultMessage, Throwable cause, HttpStatus httpStatus, ErrorCode errorCode, boolean logError) {
        super(apiResultMessage, cause);
        this.errorCode = errorCode;
        this.apiResultMessage = apiResultMessage;
        this.httpStatus = httpStatus;
        this.logError = logError;
    }

    //400
    public static FinancialAppException validationError(String apiResultMessage) {
        return validationError(apiResultMessage, false);
    }

    public static FinancialAppException validationError(String apiResultMessage, boolean logError) {
        return new FinancialAppException(apiResultMessage, HttpStatus.BAD_REQUEST, ErrorCode.VALIDATION_ERROR, logError);
    }

    //401
    public static FinancialAppException unauthorizedError(String apiResultMessage) {
        return unauthorizedError(apiResultMessage, false);
    }

    public static FinancialAppException unauthorizedError(String apiResultMessage, boolean logError) {
        return new FinancialAppException(apiResultMessage, HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED, logError);
    }

    //403
    public static FinancialAppException forbiddenError(String apiResultMessage) {
        return forbiddenError(apiResultMessage, false);
    }

    public static FinancialAppException forbiddenError(String apiResultMessage, boolean logError) {
        return new FinancialAppException(apiResultMessage, HttpStatus.FORBIDDEN, ErrorCode.FORBIDDEN, logError);
    }

    //404
    public static FinancialAppException objectNotFound(String apiResultMessage) {
        return objectNotFound(apiResultMessage, false);
    }

    public static FinancialAppException objectNotFound(String apiResultMessage, boolean logError) {
        return new FinancialAppException(apiResultMessage, HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, logError);
    }

    //500
    public static FinancialAppException serverException(String apiResultMessage) {
        return serverException(apiResultMessage, false);
    }

    public static FinancialAppException serverException(String apiResultMessage, boolean logError) {
        return new FinancialAppException(apiResultMessage, HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR, logError);
    }

}
