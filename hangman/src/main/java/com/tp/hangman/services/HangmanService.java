package com.tp.hangman.services;

import com.tp.hangman.models.HangmanGame;
import com.tp.hangman.models.HangmanViewModel;
import com.tp.hangman.persistence.HangmanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//handles our game logic
@Service
public class HangmanService {
    @Autowired
    public HangmanDao dao;

    public HangmanViewModel getGameById(Integer id) {
        HangmanGame game = dao.getGameById(id);
        return convertModel(game);
    }

    private HangmanViewModel convertModel(HangmanGame game) {
        //TODO: generate string with hidden characters for unguessed letters
        //and build view model object (using the setters)
        String partialWord = "";

        // LOGIC
        String hiddenWord = game.getHiddenWord();
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (game.getGuessedLetters().contains(hiddenWord.charAt(i))) {
                partialWord += hiddenWord.charAt(i) + " ";
            } else {
                partialWord += "* ";
            }
        }

        HangmanViewModel toReturn = new HangmanViewModel();
        toReturn.setPartialWord(partialWord);
        toReturn.setGuessedLetters(game.getGuessedLetters());

        return toReturn;
    }

    public HangmanViewModel makeGuess(Integer id, String guess) throws NullGuessException {
        if (guess == null) {
            throw new NullGuessException("Tried to guess on game " + id + " with a null guess.");
        }

        if (guess.length() != 1) {
            //TODO: make and throw a custom exception
            throw new NullGuessException("Tried to guess on game " + id + " with invalid size guess");
        }
        if (id == null) {
            //TODO: make and throw a custom exception
            throw new NullGuessException("Tried to guess on game with null id");
        }

        HangmanGame game = dao.getGameById(id);
        if (game == null) {
            return null;
        }

        game.getGuessedLetters().add(guess.charAt(0));

        // return null if there are more than 5 wrong guesses
        List<Character> wrongGuesses = game.getGuessedLetters().stream()
                .filter(c -> game.getHiddenWord().indexOf(c) == -1)
                .collect(Collectors.toList());
        if (wrongGuesses.size() > 5) {
            return null;
        }

        HangmanViewModel view = convertModel(game);

        // return null if the word has been correctly guessed
        if (view.getPartialWord().replaceAll("\\s+", "")
                .equalsIgnoreCase(game.getHiddenWord())) {
            return null;
        }

        return view;
    }

    public HangmanViewModel makeGuess(String letter) {
        throw new UnsupportedOperationException();
    }
}
