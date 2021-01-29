package com.tp.connectFour.services;

import com.tp.connectFour.models.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConnectServiceTests {

    @Autowired
    ConnectServices service;

    @BeforeEach
    public void setup() {           //throws InvalidGameIdException
        List<Board> allGames = service.getAllGames();

        for(Board toDelete: allGames){
            service.deleteGame(toDelete.getBoardId());
        }
    }

    @Test
    public void testStartGameGoldenPath(){
        Board board = service.startGame();
        assertEquals(1 , board.getBoardId());
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                assertEquals(0, board.getBoard()[row][col]);
            }
        }
    }

    @Test
    public void testGetBoardByIdGolden(){
        Board board = service.startGame();
        assertEquals(board, service.getBoardById(board.getBoardId()));
    }


}
