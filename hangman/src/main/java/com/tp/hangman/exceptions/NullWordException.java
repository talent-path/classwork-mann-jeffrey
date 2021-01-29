package com.tp.hangman.exceptions;

public class NullWordException extends Exception{
    public NullWordException(String msg) {
        super(msg);
    }

    public NullWordException(String msg, Throwable innerException) {
        super(msg, innerException);
    }
}
