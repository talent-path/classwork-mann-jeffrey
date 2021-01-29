package com.tp.library.exceptions;

public class InvalidBookIdException extends Exception {
    public InvalidBookIdException(String msg) {
        super(msg);
    }

    public InvalidBookIdException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
