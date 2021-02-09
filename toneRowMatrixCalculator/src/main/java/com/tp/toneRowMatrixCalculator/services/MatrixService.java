package com.tp.toneRowMatrixCalculator.services;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import com.tp.toneRowMatrixCalculator.persistence.ToneRowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatrixService {
    @Autowired
    ToneRowDao dao;

    public List<ToneRow> getAllMatrices() {
        return dao.getAllMatrices();
    }
}
