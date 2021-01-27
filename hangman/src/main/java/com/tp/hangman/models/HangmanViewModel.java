package com.tp.hangman.models;

import java.util.List;

public class HangmanViewModel {

    private List<Character> guessedLetters;
    private String partialWord;

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
