package com.tp.toneRowMatrixCalculator.persistence;

import org.springframework.stereotype.Repository;

@Repository
public interface ComposerWorkDao {
    boolean exists(Integer workId, Integer composerId);
    Integer[] getComposerWork (Integer workId, Integer composerId);
    void createComposerWork(Integer workId, Integer composerId);
}
