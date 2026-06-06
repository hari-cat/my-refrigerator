package org.example.myrefrigerator.refrigerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RefrigeratorErrorCode implements ErrorCode {
    REFRIGERATOR_IS_EXIST(HttpStatus.CONFLICT, "REFRIGERATOR_409", "이미 나만의 냉장고가 존재합니다."),
    REFRIGERATOR_NOT_FOUND(HttpStatus.NOT_FOUND, "REFRIGERATOR_404", "냉장고를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
