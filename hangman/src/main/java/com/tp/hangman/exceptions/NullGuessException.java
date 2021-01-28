package com.tp.hangman.exceptions;

public class NullGuessException extends Exception {
    public NullGuessException(String msg) {
        super(msg);
    }

    NullGuessException(String msg, Throwable innerException) {
        super(msg, innerException);
    }
}
