package com.tp.connectfour.persistence;

import com.tp.connectfour.models.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConnectFourInMemDao implements ConnectFourDao {
    List<Board> allBoards = new ArrayList<>();

    public ConnectFourInMemDao() {
        Board onlyGame = new Board(100);
        allBoards.add(onlyGame);
    }

    @Override
    public Board getBoardById(Integer boardId) {
        Board toReturn = null;
        for (Board board : allBoards) {
            if (board.getBoardId().equals(boardId)) {
                toReturn = board;
            }
        }

        return toReturn;
    }
}
