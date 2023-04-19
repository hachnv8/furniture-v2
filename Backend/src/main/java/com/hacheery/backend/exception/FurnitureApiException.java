package com.hacheery.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
@NoArgsConstructor
@AllArgsConstructor
public class FurnitureApiException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;
}
