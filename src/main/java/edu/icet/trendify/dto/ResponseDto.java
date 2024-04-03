package edu.icet.trendify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private Boolean status;
    private String message;
    private T data;

    public static <T> ResponseDto<T> empty(){
        return success(null, null);
    }
    public static <T> ResponseDto<T> success(T data, String message){
        String defaultMessage = "SUCCESS!";
        return ResponseDto.<T>builder()
                .status(true)
                .message(message == null ? defaultMessage : message)
                .data(data)
                .build();
    }
    public static <T> ResponseDto<T> error(String message){
        String defaultMessage = "ERROR!";
        return ResponseDto.<T>builder()
                .status(false)
                .message(message == null ? defaultMessage : message)
                .build();
    }
}
