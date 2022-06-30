package com.senacor.exceptions;

public class NotEnoughPersonException extends RuntimeException {
    public final Long count;

    public NotEnoughPersonException(Long count) {
        this.count = count;
    }
}