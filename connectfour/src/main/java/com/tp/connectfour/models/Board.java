package com.tp.connectfour.models;

public class Board {
    int boardId;
    int[][] board;

    public Integer getBoardId() {
        return this.boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int[][] getBoard() {
        return board;
    }
    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Board(Integer boardId) {
        this.boardId = boardId;
        board = new int[6][7];
    }

    public Board(Board that) {
        this.boardId = that.boardId;
        this.board = new int[6][7];
        for (int i = 0; i < that.board.length; i++) {
            for (int j = 0; j < that.board[i].length; j++) {
                this.board[i][j] = that.board[i][j];
            }
        }
    }
}
