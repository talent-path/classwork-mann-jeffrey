package com.tp.connectFour.persistence;

import com.tp.connectFour.models.Board;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectMemDao implements ConnectDao {

    List<Board> allBoard = new ArrayList<>();

    public ConnectMemDao(){
        Board onlyGame = new Board(0);
        allBoard.add(onlyGame);
    }

    @Override
    public int startGame() {
        int id = 0;

        for( Board toCheck : allBoard ){
            if( toCheck.getBoardId() > id ){
                id = toCheck.getBoardId();
            }
        }

        id++;

        Board toAdd = new Board( id );
        allBoard.add( toAdd );
        return id;
    }


    @Override
    public List<Board> getAllGames() {
        List<Board> copyList = new ArrayList<>();
        for( Board toCopy : allBoard ){
            copyList.add( new Board(toCopy) );
        }
        return copyList;
    }

    @Override
    public void deleteGame(Integer boardId) {
        int removeIndex = -1;

        for( int i = 0; i < allBoard.size(); i++ ){
            if( allBoard.get(i).getBoardId().equals(boardId)){
                removeIndex = i;
                break;
            }
        }
        allBoard.remove(removeIndex);
    }


    @Override
    public Board getBoardbyId(Integer gameId) {

        Board toReturn = null;

        for(Board toCheck : allBoard){
            if(toCheck.getBoardId().equals(gameId)){
                toReturn = new Board(toCheck);
            }
        }
        return toReturn;
    }

    public Board updateGame(Board game) {
        int id = -1;
        for( int i = 0; i < allBoard.size(); i++){
            if( allBoard.get(i).getBoardId().equals(game.getBoardId())){
                //we found the game to update
                allBoard.set(i, new Board(game) );
                id = i;
                break;
            }
        }
        return allBoard.get(id);

    }


}
