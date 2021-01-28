package com.tp.connectfour.persistence;

import com.tp.connectfour.models.Board;

public interface ConnectFourDao {

    Board getBoardById(Integer boardId);
}
