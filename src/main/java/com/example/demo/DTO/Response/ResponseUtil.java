package com.example.demo.DTO.Response;

public class ResponseUtil {
    public static ApiResponse<Void> SuccessNotData(String message) {
        return new ApiResponse<>(200, message);
    }

    public static <T> ApiResponse<T> SuccessData(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static ApiResponse<Void> Error(String message) {
        return new ApiResponse<>(500, message);
    }
    public static <T> ApiResponse<T>ErrorStatus(int status, String message) {
        return new ApiResponse<>(status, message);
    }
}
