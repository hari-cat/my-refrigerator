package org.example.myrefrigerator.RefrigeratorProduct;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RefrigeratorProductErrorCode implements ErrorCode {
    REFRIGERATOR_PRODUCT_IS_NOT_EXIST(HttpStatus.NOT_FOUND, "REFRIGERATOR_PRODUCT_404", "냉장고에 해당 상품이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
