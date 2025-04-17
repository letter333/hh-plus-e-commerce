package kr.hhplus.be.server.interfaces.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private final HttpStatus code;
    private final String message;
    private final T data;

    private ApiResponse(HttpStatus code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(HttpStatus.OK, "OK", null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK, "OK", data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(HttpStatus.OK, message, null);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(HttpStatus.OK, message, data);
    }

    public static <T> ApiResponse<T> fail(HttpStatus code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
