package com.seung.shopmall.exception;

import lombok.Getter;

public class DuplicateResourceException extends RuntimeException {

    @Getter
    private String errKey;

    public DuplicateResourceException(String errKey) {
        super(errKey);
        this.errKey = errKey;
    }
}
