package edu.icet.trendify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private Boolean status;
    private String message;
    private T data;

    public static <T> ResponseDto<T> empty(){
        return success(null);
    }
    public static <T> ResponseDto<T> success(T data){
        return ResponseDto.<T>builder()
                .status(true)
                .message("SUCCESS!")
                .data(data)
                .build();
    }
    public static <T> ResponseDto<T> error(){
        return ResponseDto.<T>builder()
                .status(false)
                .message("ERROR!")
                .build();
    }
}
