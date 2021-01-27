package com.tp.hangman.persistence;

//DAO = Data Access Object
//  naming is conventional for Java
//  but unusual for other Languages

import com.tp.hangman.models.HangmanGame;

public interface HangmanDao {
    HangmanGame getGameById(Integer id);
}
