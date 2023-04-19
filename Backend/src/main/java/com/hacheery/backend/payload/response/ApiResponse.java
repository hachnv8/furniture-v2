package com.hacheery.backend.payload.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Created by HachNV on 19/04/2023
 */
@Data
@JsonPropertyOrder({
        "success",
        "message"
})
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse implements Serializable {
    private static final Long serialize = 1L;
    private HttpStatus status;
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }
}
