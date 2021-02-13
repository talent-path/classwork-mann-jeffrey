package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ToneRowDao {

    Map<Integer, Matrix> getAllMatrices();

    Map<Integer, ToneRow> getAllToneRows();

    Matrix getMatrixById(Integer toneRowId);

    ToneRow getToneRowById(Integer toneRowId);

    ToneRow createToneRow(Integer workId);
}
