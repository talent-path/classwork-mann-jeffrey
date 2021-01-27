package com.tp.hangman.models;

import java.util.ArrayList;
import java.util.List;

public class HangmanGame {
//    fields:
//    word to guess
//
    Integer id;
    String hiddenWord;
    List<Character> guessedLetters;

    public HangmanGame(String hiddenWord, Integer id) {
        this.id = id;
        this.hiddenWord = hiddenWord;
        this.guessedLetters = new ArrayList<>();
    }

    public HangmanGame(String hiddenWord, Integer id, List<Character> guessedLetters) {
        this.id = id;
        this.hiddenWord = hiddenWord;
        this.guessedLetters = guessedLetters;
    }

    public Integer getId() {
        return id;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }
}
