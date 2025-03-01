package com.example.demo.DTO;

public class ResponseUtil {
    public static ApiResponse SuccessNotData(String message) {
        return new ApiResponse(200, message);
    }

    public static <T> ApiResponse<T> SuccessData(String message, T data) {
        return new ApiResponse(200, message, data);
    }

    public static ApiResponse Error(String message) {
        return new ApiResponse(500, message);
    }
}
