package com.tp.hangman.persistence;

import com.tp.hangman.exceptions.InvalidGameIdException;
import com.tp.hangman.exceptions.NullWordException;
import com.tp.hangman.models.HangmanGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class HangmanInMemDaoTest {
    @Autowired
    HangmanInMemDao toTest;

    //runs before each test method
    @BeforeEach
    public void setup() throws NullWordException, InvalidGameIdException {
        List<HangmanGame> allGames = toTest.getAllGames();
        for (HangmanGame toRemove : allGames) {
            toTest.deleteGame(toRemove.getId());
        }

        //create new game with word "zebra" and id 1;
        toTest.startGame("zebra");
    }
    //golden path test
    //  good input -> good output
    @Test
    public void testStartGameGoldenPath() {
        try {
            //1. Arrange
            String testWord = "test";
            //2. Act
            //      call the method
            //      save any outputs
            int testableId = toTest.startGame(testWord);
            //3. Assert
            assertEquals(2, testableId);

            int nextId = toTest.startGame("someword");
            assertEquals(3, nextId);

            HangmanGame validationGame = toTest.getGameById(2);
            assertEquals(testWord, validationGame.getHiddenWord());
        } catch (NullWordException ex) {
            fail();
        }
    }

    @Test
    public void testStartGameNullWord() {
        try {
            String testWord = null;

            int testableId = toTest.startGame(testWord);

            fail();
        } catch (NullWordException ex) {

        }
    }

}
