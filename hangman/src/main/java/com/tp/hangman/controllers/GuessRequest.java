package com.tp.hangman.controllers;

public class GuessRequest {
    private String guess;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getGuess() {
        return guess;
    }
}
