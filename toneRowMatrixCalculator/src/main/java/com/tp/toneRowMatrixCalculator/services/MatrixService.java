package com.tp.toneRowMatrixCalculator.services;

import com.tp.toneRowMatrixCalculator.models.*;
import com.tp.toneRowMatrixCalculator.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

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

    public Map<Integer, Matrix> getMatrixById(Integer id) {
        throw new UnsupportedOperationException();
//        return dao.getMatrixById(id);
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

//        if (composerName == null) {
//            throw new IllegalArgumentException("Cannot create a Tone Row with a null composer");
//        }
//        if (workTitle == null) {
//            throw new IllegalArgumentException("Cannot create a Tone Row with a null work title");
//        }

//        Work workForToneRow = createWork(workTitle);
//
//        Composer composerForToneRow = createComposer(composerName);
//
//        if (!cwDao.exists(workForToneRow.getWorkId(), composerForToneRow.getComposerId())) {
//            cwDao.createComposerWork(workForToneRow.getWorkId(), composerForToneRow.getComposerId());
//        }

        ToneRow newToneRow = toneRowDao.createToneRow(workId);

        newToneRow.setNoteOrder(
                createNotesOnToneRow(
                        noteOrder,
                        newToneRow.getToneRowId()
                )
        );

        return newToneRow;
    }

    private Note[] createNotesOnToneRow(Integer[] noteOrder, Integer toneRowId) {
        Note[] toReturn = new Note[12];
        for (int i = 0; i < 12; i++) {
            int pitchClass = noteOrder[i];
            int orderIndex = i;
            toReturn[i] = noteDao.createNote(pitchClass, orderIndex, toneRowId);
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
        if (!composerDao.exists(composerName)) {
            toReturn = composerDao.createComposer(composerName);
        } else {
            toReturn = composerDao.getComposerByName(composerName);
        }
        return toReturn;
    }
}
