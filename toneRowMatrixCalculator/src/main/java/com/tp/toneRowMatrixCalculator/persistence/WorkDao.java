package com.tp.toneRowMatrixCalculator.persistence;

import com.tp.toneRowMatrixCalculator.models.Work;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDao {
    Work createWork(String workTitle);

    Work getWorkByTitle(String workTitle);

    boolean exists(String workTitle);
}
