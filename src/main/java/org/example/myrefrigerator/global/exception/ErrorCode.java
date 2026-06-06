package org.example.myrefrigerator.global.exception;


import org.springframework.http.HttpStatus;

public interface ErrorCode {
     HttpStatus getStatus();
     String getCode();
     String getMessage();
}
