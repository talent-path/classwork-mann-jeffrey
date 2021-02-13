package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Profile("serviceTesting")
public class ToneRowInMemDao implements ToneRowDao {

    @Override
    public Map<Integer, Matrix> getAllMatrices() {
        return null;
    }

    @Override
    public Map<Integer, ToneRow> getAllToneRows() {
        return null;
    }

    @Override
    public Matrix getMatrixById(Integer toneRowId) {
        return null;
    }

    @Override
    public ToneRow getToneRowById(Integer toneRowId) {
        return null;
    }

    @Override
    public ToneRow createToneRow(Integer workId) {
        return null;
    }
}
