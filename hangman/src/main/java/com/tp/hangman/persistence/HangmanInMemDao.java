package com.tp.hangman.persistence;

import com.tp.hangman.exceptions.InvalidGameIdException;
import com.tp.hangman.models.HangmanGame;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HangmanInMemDao implements HangmanDao {

    //    Map<Integer, HangmanGame> allGames;
    List<HangmanGame> allGames = new ArrayList<>();

    public HangmanInMemDao() {
    }

    @Override
    public List<HangmanGame> getAllGames() {
        List<HangmanGame> copyList = new ArrayList<>();
        for (HangmanGame toCopy : allGames) {
            copyList.add(new HangmanGame(toCopy));
        }
        return copyList;
    }

    @Override
    public HangmanGame getGameById(Integer id) {
        return allGames.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void updateGame(HangmanGame game) {
        for (int i = 0; i < allGames.size(); i++) {
            if (allGames.get(i).getId().equals(game.getId())) {
                allGames.set(i, new HangmanGame(game));
            }
        }
    }

    @Override
    public int startGame(String word) {
        int id = 0;

        for (HangmanGame toCheck : allGames) {
            if (toCheck.getId() > id) {
                id = toCheck.getId();
            }
        }

        id++;

        HangmanGame newGame = new HangmanGame(id, word);
        allGames.add(newGame);
        return id;
    }

    @Override
    public void deleteGame(Integer gameId) throws InvalidGameIdException {
        int removeIndex = -1;

        for (int i = 0; i < allGames.size(); i++) {
            if (allGames.get(i).getId().equals(gameId)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            allGames.remove(removeIndex);
        } else {
            throw new InvalidGameIdException("Could not find game with id " + gameId + "to delete.");
        }
    }
}
