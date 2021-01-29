package com.tp.hangman.services;

import com.tp.hangman.exceptions.InvalidGameIdException;
import com.tp.hangman.exceptions.InvalidGuessException;
import com.tp.hangman.exceptions.NullGuessException;
import com.tp.hangman.exceptions.NullWordException;
import com.tp.hangman.models.HangmanViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HangmanServiceTest {
    @Autowired
    HangmanService testService;

    @BeforeEach
    public void setup() throws InvalidGameIdException {
        List<HangmanViewModel> allGames = testService.getAllGames();

        for (HangmanViewModel toDelete : allGames) {
            testService.deleteGame(toDelete.getId());
        }
    }

    @Test
    public void startGameTestGoldenPath() {
        RNG.reseed(12345);
        try {
            HangmanViewModel game = testService.startGame();

            assertEquals(0, game.getId());
            assertEquals("* * * * * * ", game.getPartialWord());
            assertTrue(game.getGuessedLetters().isEmpty());
        } catch (InvalidGameIdException | NullWordException e) {
            fail();
        }
    }

    @Test
    public void makeGuessTestGoldenPath() {
        // arrange
        RNG.reseed(12345);
        try {
            HangmanViewModel game = testService.startGame();
            HangmanViewModel gameAfterMiss = testService.makeGuess(game.getId(), "a");
            HangmanViewModel gameAfterHit = testService.makeGuess(game.getId(), "z");

            assertEquals("* * * * * * ", gameAfterMiss.getPartialWord());
//            assertEquals(1, gameAfterMiss.getGuessedLetters().size());
//            assertEquals('a', gameAfterMiss.getGuessedLetters().get(0));

//            assertEquals("* * * * * z ", gameAfterHit.getPartialWord());
        } catch (NullWordException | NullGuessException | InvalidGuessException | InvalidGameIdException e) {
            fail();
        }
    }

}
