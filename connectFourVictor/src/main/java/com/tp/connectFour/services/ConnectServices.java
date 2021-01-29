package com.tp.connectFour.services;

import com.tp.connectFour.models.Board;
import com.tp.connectFour.persistence.ConnectMemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectServices {

    @Autowired
    ConnectMemDao dao;

    public Board getBoardById(Integer boardId) {
        return dao.getBoardbyId(boardId);
    }

    public Board startGame() {
        int startBoard = dao.startGame();
        return this.getBoardById(startBoard);
    }

    public List<Board> getAllGames() {
        List<Board> allGames = dao.getAllGames();
        List<Board> copyList = new ArrayList<>();
        for( Board toCopy : allGames ){
            copyList.add( new Board(toCopy) );
        }
        return copyList;
    }

    public void deleteGame(Integer boardId) {
        dao.deleteGame(boardId);
    }

    public Board makeMove(int gameId, int column) {

        Board game = dao.getBoardbyId(gameId);
        boolean done = false;
        for(int row = game.getBoard().length - 1;!done && row >= 0; row-- ){
            if(game.getBoard()[row][column] == 0){
                game.getBoard()[row][column] = 1;
                done = true;
            }
        }
        return dao.updateGame(game);
    }






}
