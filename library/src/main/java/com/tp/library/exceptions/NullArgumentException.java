package com.tp.library.exceptions;

public class NullArgumentException extends Exception {
    public NullArgumentException(String msg) {
        super(msg);
    }

    public NullArgumentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
