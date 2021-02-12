package com.tp.toneRowMatrixCalculator.persistence;

import com.tp.toneRowMatrixCalculator.models.Note;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao {
    Note createNote(int pitchClass, int orderIndex, Integer toneRowId);
}
