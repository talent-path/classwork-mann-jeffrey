package com.tp.hangman.exceptions;

public class InvalidGuessException extends Exception {
    public InvalidGuessException(String msg) {
        super(msg);
    }

    InvalidGuessException(String msg, Throwable innerException) {
        super(msg, innerException);
    }
}
