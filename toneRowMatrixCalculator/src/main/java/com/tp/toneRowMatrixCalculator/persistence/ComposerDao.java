package com.tp.toneRowMatrixCalculator.persistence;

import com.tp.toneRowMatrixCalculator.models.Composer;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposerDao {
    Composer createComposer(String composer);

    Composer getComposerByName(String composer);

    boolean exists(String composer);
}
