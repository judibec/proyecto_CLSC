package com.financialLab.financedevapp.dto.responses;

import com.financialLab.financedevapp.exception.ErrorCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private static final String OPERATION_SUCCESSFUL = "Operation successful";

    @NonNull
    private String message;
    private ErrorCode errorCode;
    private boolean success;
    private T content;

    private ResponseDTO(String message, T content) {
        this(message, null, true, content);
    }

    private ResponseDTO(String message, ErrorCode errorCode) {
        this(message, errorCode, false, null);
    }

    public static <T> ResponseDTO<T> ofError(ErrorCode reason, String message) {
        return new ResponseDTO<>(message, reason);
    }

    public static <T> ResponseDTO<T> ofSuccess(T content) {
        return new ResponseDTO<>(OPERATION_SUCCESSFUL, content);
    }

}
