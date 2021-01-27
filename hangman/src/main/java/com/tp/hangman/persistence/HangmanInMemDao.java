package com.tp.hangman.persistence;

import com.tp.hangman.models.HangmanGame;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HangmanInMemDao implements HangmanDao {

    //    Map<Integer, HangmanGame> allGames;
    public List<HangmanGame> allGames = new ArrayList<>();

    public HangmanInMemDao(){
        int id = 1;
        String[] words = {"classroom", "affair", "apartment", "application",
                "procedure", "sympathy", "son", "revenue",
                "road", "tradition", "equipment", "profession",
                "alcohol", "person", "instruction"};
        for (String word : words) {
            allGames.add(new HangmanGame(word, id));
            id++;
        }
    }

    @Override
    public HangmanGame getGameById(Integer id) {
//        HangmanGame toReturn = null;
//
//        for (HangmanGame toCheck :
//                allGames) {
//            if (toCheck.getId() == id) {
//                toReturn = toCheck;
//                break;
//            }
//        }
//        return toReturn;

        return allGames.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    public List<HangmanGame> getVowelGames() {
        List<HangmanGame> toReturn = new ArrayList<>();

        for (HangmanGame toCheck : allGames) {
            String word = toCheck.getHiddenWord().toLowerCase();
            if (word.charAt(0) == 'a' ||
                    word.charAt(0) == 'e' ||
                    word.charAt(0) == 'i' ||
                    word.charAt(0) == 'o' ||
                    word.charAt(0) == 'u') {
                toReturn.add(toCheck);
            }
        }
        return toReturn;
    }
}
