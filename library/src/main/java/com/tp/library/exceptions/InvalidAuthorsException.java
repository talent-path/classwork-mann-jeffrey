package com.tp.library.exceptions;

public class InvalidAuthorsException extends Exception{
    public InvalidAuthorsException(String msg) {
        super(msg);
    }

    public InvalidAuthorsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
