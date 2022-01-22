package com.youthcon.tdd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFountException extends RuntimeException{
    public ReviewNotFountException(String message) {
        super(message);
    }
}
