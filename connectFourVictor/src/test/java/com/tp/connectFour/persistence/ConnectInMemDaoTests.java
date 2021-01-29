package com.tp.connectFour.persistence;

import com.tp.connectFour.exceptions.NullWordException;
import com.tp.connectFour.models.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class ConnectInMemDaoTests {

    //test functions should never have inputs
    //all void methods
        //all unit test should be void
        //no need to return anything



    @Autowired
    ConnectMemDao toTest;

    //runs before each @Test method
    @BeforeEach
    public void setup(){                    //throws InvalidGameIdException
        List<Board> allGames = toTest.getAllGames();
        for(Board toRemove: allGames){
            toTest.deleteGame(toRemove.getBoardId());
        }
        //TODO a method that initializes the game
       // toTest.startGame()
    }


    //golden path test
    //good input -> good output
    @Test
    public void testStartGame(){
        //unit test phases
        //1. Arrange
        //2. Act
        //3. Assert
            //make sure the method did what it ws supposed to do

        //1.
        //2.
        int id = toTest.startGame();

        //3.
        assertEquals(1, id);
    }

//    @Test
//    public void testStartGameNullWord(){
//        //unit test phases
//        //1. Arrange
//        //2. Act
//        //3. Assert
//        //make sure the method did what it ws supposed to do
//try{
//
//        //1.
//        String testWord = null;
//
//        //2.
//        int id = toTest.startGame();
//
//        //3.
//        assertEquals(2, id);
//        //we should only get here if it fails
//        //we want an exception
//        //we fail test
//    fail();
//    //
//    }catch (NullWordException ex){
//        //we got the exception we wanted
//        fail();
//    }
//    }
}
