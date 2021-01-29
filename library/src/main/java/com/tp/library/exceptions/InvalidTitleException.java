package com.tp.library.exceptions;

public class InvalidTitleException extends Exception {
    public InvalidTitleException(String msg) {
        super(msg);
    }

    public InvalidTitleException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
