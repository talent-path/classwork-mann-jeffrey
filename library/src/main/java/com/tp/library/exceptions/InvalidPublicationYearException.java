package com.tp.library.exceptions;

public class InvalidPublicationYearException extends Exception{
    public InvalidPublicationYearException(String msg) {
        super(msg);
    }

    public InvalidPublicationYearException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
