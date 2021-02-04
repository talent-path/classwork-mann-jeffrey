package com.tp.connectFour.controllers;

import com.tp.connectFour.models.Board;
import com.tp.connectFour.services.ConnectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    @Autowired
    ConnectServices service;


    @GetMapping("/game")
    public List<Board> getAllBoards(){
        return service.getAllGames();
    }

    @PostMapping("/begin")
    public Board startGame(){
        Board game = null;
//        try {
            game = service.startGame();
//        } catch (NullWordException e) {
//            e.getMessage();
//        }

        return game;
    }

    @GetMapping("/game/{gameId}")
    public Board getBoard(@PathVariable Integer gameId){
        Board thisOne = service.getBoardById(gameId);
        return thisOne;
    }

    @PostMapping("/game")
    public Board makeMove(@RequestBody MoveRequest move){
        return service.makeMove(move.getGameId(), move.getColumn());
    }
}
