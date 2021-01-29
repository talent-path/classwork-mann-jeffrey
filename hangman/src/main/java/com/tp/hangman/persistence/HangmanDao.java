package com.tp.hangman.persistence;

//DAO = Data Access Object
//  naming is conventional for Java
//  but unusual for other Languages

import com.tp.hangman.exceptions.InvalidGameIdException;
import com.tp.hangman.exceptions.NullWordException;
import com.tp.hangman.models.HangmanGame;

import java.util.List;

public interface HangmanDao {
    HangmanGame getGameById(Integer id);

    List<HangmanGame> getAllGames();

    void updateGame(HangmanGame game);

    int startGame(String word) throws NullWordException;

    void deleteGame(Integer gameId) throws InvalidGameIdException;
}
