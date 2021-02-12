package com.tp.toneRowMatrixCalculator.persistence;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Profile("daoTesting")
public class ToneRowInMemDao implements ToneRowDao {

    @Override
    public Map<Integer, Matrix> getAllMatrices() {
        return null;
    }

    @Override
    public ToneRow createToneRow(Integer workId) {
        return null;
    }
}
