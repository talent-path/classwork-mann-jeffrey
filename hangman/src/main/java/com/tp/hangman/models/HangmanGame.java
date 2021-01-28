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

    public HangmanGame(Integer id, String hiddenWord) {
        this.id = id;
        this.hiddenWord = hiddenWord;
        guessedLetters = new ArrayList<>();
    }

    public HangmanGame(Integer id, String hiddenWord, List<Character> guessedLetters) {
        this.id = id;
        this.hiddenWord = hiddenWord;
        this.guessedLetters = guessedLetters;
    }

    //copy constructor
    public HangmanGame( HangmanGame that ) {
        this.id = that.id;
        this.hiddenWord = that.hiddenWord;
        this.guessedLetters = new ArrayList<>();
        for (Character toCopy:that.guessedLetters) {
            this.guessedLetters.add(toCopy);
        }
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
