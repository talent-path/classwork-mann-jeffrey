package com.tp.hangman.controllers;

import com.tp.hangman.models.HangmanViewModel;
import com.tp.hangman.persistence.HangmanInMemDao;
import com.tp.hangman.services.HangmanService;
import com.tp.hangman.services.NullGuessException;
import com.tp.hangman.services.RNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/game")
    public HangmanViewModel getRandomGame() {
        HangmanInMemDao memory = (HangmanInMemDao) service.dao;
        return service.getGameById(RNG.rollDice(memory.allGames.size()));
    }

    @GetMapping("/game/{id}")
    public HangmanViewModel getGameById(@PathVariable Integer id) {
        return service.getGameById(id);
    }

    @GetMapping("/game/{id}/guess/{letter}")
    public HangmanViewModel guessLetter(@PathVariable Integer id, @PathVariable String letter)
            throws NullGuessException {
        HangmanViewModel toReturn = null;


        try {
            toReturn = service.makeGuess(id, letter);
        } catch (NullGuessException ex) {
            ResponseEntity<HangmanViewModel> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST, );
        }

        return ResponseEntity.ok(toReturn);
    }
}
