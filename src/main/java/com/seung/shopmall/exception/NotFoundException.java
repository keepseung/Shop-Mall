package com.seung.shopmall.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    @Getter
    private String errKey;

    public NotFoundException(String errKey) {
        super(errKey);
        this.errKey = errKey;
    }
}
