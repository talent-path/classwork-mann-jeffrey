package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Work;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface WorkDao {
    Work createWork(String workTitle);

    Work getWorkByTitle(String workTitle);

    boolean exists(String workTitle);

    Work getWorkById(Integer workId);

    Map<Integer, Work> getAllWorks();
}
