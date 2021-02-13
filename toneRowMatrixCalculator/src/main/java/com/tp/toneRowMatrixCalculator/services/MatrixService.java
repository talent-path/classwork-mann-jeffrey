package com.tp.toneRowMatrixCalculator.services;

import com.tp.toneRowMatrixCalculator.exceptions.InvalidIdException;
import com.tp.toneRowMatrixCalculator.models.*;
import com.tp.toneRowMatrixCalculator.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatrixService {
    @Autowired
    ToneRowDao toneRowDao;

    @Autowired
    NoteDao noteDao;

    @Autowired
    WorkDao workDao;

    @Autowired
    ComposerDao composerDao;

    @Autowired
    ComposerWorkDao cwDao;

    public Map<Integer, Matrix> getAllMatrices() {
        return toneRowDao.getAllMatrices();
    }

    public Map<Integer, ToneRow> getAllToneRows() {
        return toneRowDao.getAllToneRows();
    }

    public Matrix getMatrixById(Integer id) throws InvalidIdException {
        Matrix toReturn = toneRowDao.getMatrixById(id);
        if (toReturn == null) throw new InvalidIdException("No Matrix with id: " + id);
        return toReturn;
    }

    public ToneRow getToneRowById(Integer id) throws InvalidIdException {
        ToneRow toReturn = toneRowDao.getToneRowById(id);
        if (toReturn == null) throw new InvalidIdException("No Matrix with id: " + id);
        return toReturn;
    }

    public ToneRow createToneRow(Integer[] noteOrder, Integer workId) {
        if (noteOrder == null) {
            throw new IllegalArgumentException("Cannot create a Tone Row with a null set of notes");
        }
        if (Arrays.stream(noteOrder).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Cannot create a Tone Row with any null notes");
        }
        if (noteOrder.length != 12) {
            throw new IllegalArgumentException(
                    String.format(
                            "Cannot create a Tone Row with %s 12 notes",
                            noteOrder.length < 12 ? "less than" : "more than"
                    )
            );
        }
        if (hasDuplicates(noteOrder)) {
            throw new IllegalArgumentException("Cannot create a Tone Row with duplicate notes");
        }

        ToneRow newToneRow = toneRowDao.createToneRow(workId);

        newToneRow.setNoteOrder(
                createNotesOnToneRow(
                        noteOrder,
                        newToneRow.getToneRowId()
                )
        );

        return newToneRow;
    }

    private boolean hasDuplicates(Integer[] noteOrder) {
        Set<Integer> seen = new HashSet<Integer>();
        for (Integer i : noteOrder) {
            if (seen.contains(i)) return true;
            seen.add(i);
        }
        return false;
    }

    private Note[] createNotesOnToneRow(Integer[] noteOrder, Integer toneRowId) {
        Note[] toReturn = new Note[12];
        for (int orderIndex = 0; orderIndex < 12; orderIndex++) {
            int pitchClass = noteOrder[orderIndex];
            toReturn[orderIndex] = noteDao.createNote(pitchClass, orderIndex, toneRowId);
        }
        return toReturn;
    }

    public Work createWork(String workTitle) {
        Work toReturn;
        if (workDao.exists(workTitle)) {
            toReturn = workDao.getWorkByTitle(workTitle);
        } else {
            toReturn = workDao.createWork(workTitle);
        }
        return toReturn;
    }

    public Composer createComposer(String composerName) {
        Composer toReturn;
        if (composerDao.exists(composerName)) {
            toReturn = composerDao.getComposerByName(composerName);
        } else {
            toReturn = composerDao.createComposer(composerName);
        }
        return toReturn;
    }
}
