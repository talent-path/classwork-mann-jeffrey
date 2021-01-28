package com.tp.connectfour.services;

import com.tp.connectfour.models.Board;
import com.tp.connectfour.persistence.ConnectFourDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectFourService {
    @Autowired
    ConnectFourDao dao;
    public Board getBoardById(Integer boardId) {
        return dao.getBoardById(boardId);
    }

    public
}
