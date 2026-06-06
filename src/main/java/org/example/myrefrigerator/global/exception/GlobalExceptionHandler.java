package org.example.myrefrigerator.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.myrefrigerator.global.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException e,
            HttpServletRequest request
    ){
        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response = ErrorResponse.of(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getStatus().value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {

        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("validation error");

        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(
                        "VALIDATION_ERROR",
                        message,
                        400,
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception e,
            HttpServletRequest request
    ) {
        log.error("Unhandled exception", e);

        return ResponseEntity.internalServerError()
                .body(ErrorResponse.of(
                        "INTERNAL_ERROR",
                        "서버 오류가 발생했습니다.",
                        500,
                        request.getRequestURI()
                ));
    }
}
