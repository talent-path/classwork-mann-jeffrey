package com.tp.hangman.services;

import com.tp.hangman.exceptions.InvalidGameIdException;
import com.tp.hangman.exceptions.InvalidGuessException;
import com.tp.hangman.exceptions.NullGuessException;
import com.tp.hangman.exceptions.NullWordException;
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
    HangmanDao dao;
    String[] possibleWords = {"classroom", "affair", "apartment", "application",
            "procedure", "sympathy", "son", "revenue",
            "road", "tradition", "equipment", "profession",
            "alcohol", "person", "instruction"};

    public List<HangmanViewModel> getAllGames() throws InvalidGameIdException {
        List<HangmanGame> games = dao.getAllGames();
        List<HangmanViewModel> converted = new ArrayList<>();
        if (games == null) {
            throw new InvalidGameIdException("There are no games.");
        }
        for (HangmanGame toConvert : games) {
            converted.add(convertModel(toConvert));
        }
        return converted;
    }

    public HangmanViewModel getGameById(Integer id) throws InvalidGameIdException {
        HangmanGame game = dao.getGameById(id);
        if (game == null) {
            throw new InvalidGameIdException("Game with id " + id + " does not exist.");
        }
        return convertModel(game);
    }

    public HangmanViewModel makeGuess(Integer id, String guess)
            throws NullGuessException, InvalidGuessException, InvalidGameIdException {
        // throw exception if guess null
        if (guess == null) {
            throw new NullGuessException("Tried to guess on game " + id + " with a null guess.");
        }
        // throw exception if guess too long
        if (guess.length() != 1) {
            //TODO: make and throw a custom exception
            throw new InvalidGuessException("A guess of " + guess + " is too long.");
        }
        //throw exception if null game id
        if (id == null) {
            //TODO: make and throw a custom exception
            throw new InvalidGameIdException("Tried to guess on game with null id.");
        }

        HangmanGame game = dao.getGameById(id);
        // throw exception if invalid game id
        if (game == null) {
            throw new InvalidGameIdException("Game with id " + id + " does not exist.");
        }

        game.getGuessedLetters().add(guess.charAt(0));
        dao.updateGame(game);

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

    private HangmanViewModel convertModel(HangmanGame game) {
        //TODO: generate string with hidden characters for unguessed letters
        //and build view model object (using the setters)
        String partialWord = "";

        // LOGIC
        String hiddenWord = game.getHiddenWord();
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (game.getGuessedLetters() != null) {
                if (game.getGuessedLetters().contains(hiddenWord.charAt(i))) {
                    partialWord += hiddenWord.charAt(i) + " ";
                } else {
                    partialWord += "* ";
                }
            }
        }

        HangmanViewModel toReturn = new HangmanViewModel();
        toReturn.setPartialWord(partialWord);
        toReturn.setGuessedLetters(game.getGuessedLetters());

        return toReturn;
    }

    public HangmanViewModel startGame() throws InvalidGameIdException, NullWordException {
        //1. make new HangmanGame
        //2. insert game into dao
        //3. get back id from dao
        String gameWord = possibleWords[RNG.rollDice(possibleWords.length - 1)];
        int newGameId = dao.startGame(gameWord);

        return this.getGameById(newGameId);
    }

    public void deleteGame(Integer gameId) throws InvalidGameIdException {
        dao.deleteGame(gameId);
    }
}
