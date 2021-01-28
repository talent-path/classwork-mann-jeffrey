package com.tp.hangman.models;

import java.util.List;

public class HangmanViewModel {

    public int id;
    private List<Character> guessedLetters;
    private String partialWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(List<Character> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

    public String getPartialWord() {
        return partialWord;
    }

    public void setPartialWord(String partialWord) {
        this.partialWord = partialWord;
    }
}
