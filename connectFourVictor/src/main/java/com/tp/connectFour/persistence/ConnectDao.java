package com.tp.connectFour.persistence;

import com.tp.connectFour.models.Board;

import java.util.List;

public interface ConnectDao {

    Board getBoardbyId(Integer gameId);

    int startGame();

    List<Board> getAllGames();

    void deleteGame(Integer boardId);
}
