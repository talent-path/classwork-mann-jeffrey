package com.tp.connectFour.models;

public class Board {
    private Integer boardId;
    private Integer[][] board;

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer[][] getBoard() {
        return board;
    }

    public void setBoard(Integer[][] board) {
        this.board = board;
    }

    //creates new empty board
    public Board(Integer gameId){
        this.boardId = gameId;
        board = new Integer[6][7];
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                this.board[row][col] = 0;
            }
        }
    }

    //update game board with a different state of board
    public Board(Integer gameId, Integer[][] board){
        this.boardId = gameId;
        this.board = board;
    }

    //copy to new board and instantiate new array with that
    public Board(Board that){
        this.boardId = that.boardId;
        this.board = new Integer[6][7];
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                this.board[row][col] = that.board[row][col];
            }
        }
    }

    public Integer getBoardId() {
        return boardId;
    }
}
