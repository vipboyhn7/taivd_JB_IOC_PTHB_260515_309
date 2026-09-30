package com.exam.common.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Object errors;
    private HttpStatus httpStatus;

    public static <T> ApiResponse<T> success(T data, String message, HttpStatus httpStatus) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(message)
                .httpStatus(httpStatus)
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> error(Object errors, String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .errors(errors)
                .message(message)
                .httpStatus(status)
                .success(false)
                .build();
    }
    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .message(message)
                .httpStatus(status)
                .success(false)
                .build();
    }
}
