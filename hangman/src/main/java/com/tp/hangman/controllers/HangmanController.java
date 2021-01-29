package com.tp.hangman.controllers;

import com.tp.hangman.exceptions.InvalidGameIdException;
import com.tp.hangman.exceptions.InvalidGuessException;
import com.tp.hangman.exceptions.NullGuessException;
import com.tp.hangman.exceptions.NullWordException;
import com.tp.hangman.models.HangmanViewModel;
import com.tp.hangman.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//provides two methods
//getGameByID() (should show word with guessed letters hidden and all correctly guessed letters)
//guessLetter() (should take a game and a letter to guess for that game)
@RestController
public class HangmanController {
    //autowired will automatically bring in any
    // "bean" which is defined as a "component"
    // several annotations (@) come from @Component
    // including @Service and @Repository
    // but we can also use @Component
    @Autowired
    HangmanService service;

    //CRUD
    // Create - making new data
    // Read - retrieving data
    // Update - changing existing data
    // Delete - removing data

    @GetMapping("/game")
    public ResponseEntity getAllGames() throws InvalidGameIdException {
        List<HangmanViewModel> toReturn = null;
        try {
            toReturn = service.getAllGames();
        } catch (InvalidGameIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity getGameById(@PathVariable Integer id) throws InvalidGameIdException {
        HangmanViewModel toReturn = null;
        try {
            toReturn = service.getGameById(id);;
        } catch (InvalidGameIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

    @PostMapping("/guess")
    public ResponseEntity guessLetter(@RequestBody GuessRequest request)
            throws NullGuessException, InvalidGuessException, InvalidGameIdException{
        HangmanViewModel toReturn = null;

        try {
            toReturn = service.makeGuess(request.getId(), request.getGuess());
        } catch (NullGuessException | InvalidGuessException | InvalidGameIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(toReturn);
    }

    @PostMapping("/begin")
    public ResponseEntity startNewGame() {
        HangmanViewModel newGame = null;
        try {
            newGame = service.startGame();
        } catch (InvalidGameIdException | NullWordException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(newGame);
    }

    @DeleteMapping("/delete/{gameId}")
    public String deleteGame( @PathVariable Integer gameId ){
        try {
            service.deleteGame( gameId );
            return "Game " + gameId + " successfully deleted.";
        } catch (InvalidGameIdException e) {
            return e.getMessage();
        }

    }
}
