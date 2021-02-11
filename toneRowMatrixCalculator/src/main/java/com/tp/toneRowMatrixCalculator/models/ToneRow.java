package com.tp.toneRowMatrixCalculator.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ToneRow {
    public Note[] noteOrder;
    private Integer toneRowId;
    private Integer workId;

    public Note[] getNoteOrder() {
        return noteOrder;
    }

    public void setNoteOrder(Note[] noteOrder) {
        this.noteOrder = noteOrder;
    }

    public Integer getToneRowId() {
        return toneRowId;
    }

    public void setToneRowId(Integer toneRowId) {
        this.toneRowId = toneRowId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Matrix generateMatrix() {
        Matrix generated = new Matrix();
//        generated.setPrimes(generatePrimes());
//        generated.setInversions(generateInversions());
//        generated.setRetrogrades(generateRetrogrades());
//        generated.setRetrogradeInversions(generateRetrogradeInversions());
        for (int i = 0; i < generated.matrix.length; i++) {
            for (int j = 0; j < generated.matrix[i].length; j++) {
                generated.matrix[i][j] = i%2==0 ? this.noteOrder[j].getValue() : this.invert().noteOrder[j].getValue();
            }
        }

        return generated;
//        throw new UnsupportedOperationException();
    }

//    private Map<String, Note[]> generatePrimes() {
//        Map<String, Note[]> toReturn = new HashMap<>();
//
//        throw new UnsupportedOperationException();
//    }

    private ToneRow transpose(int value) {
        ToneRow toReturn = new ToneRow();
        toReturn.setToneRowId(toneRowId);
        toReturn.setWorkId(workId);

        Note[] transposedNoteOrder = new Note[12];
        for (Note toTranspose : noteOrder) {
            Note transposed = toTranspose.transpose(value);
            transposedNoteOrder[transposed.getOrderIndex()] = transposed;
        }
        toReturn.setNoteOrder(transposedNoteOrder);

        return toReturn;
    }

    private ToneRow invert() {
        ToneRow toReturn = new ToneRow();
        toReturn.setToneRowId(toneRowId);
        toReturn.setWorkId(workId);

        Note[] invertedNoteOrder = new Note[12];
        invertedNoteOrder[0] = noteOrder[0];
        for (int i = 1; i < noteOrder.length; i++) {
            Note inverted = new Note();
            int interval = noteOrder[i-1].interval(noteOrder[i]);

            // this should not compare to prime row...
            NoteInfo invertedValue = NoteInfo.getByValue(
                    invertedNoteOrder[i-1].transpose(-interval).getValue()
            );

            inverted.setNoteId(noteOrder[i].getNoteId());
            inverted.setOrderIndex(noteOrder[i].getOrderIndex());
            inverted.setNoteInfo(invertedValue);

            invertedNoteOrder[i] = inverted;
        }
        toReturn.setNoteOrder(invertedNoteOrder);

        return toReturn;
    }

    private ToneRow retrograde() {
        ToneRow toReturn = new ToneRow();
        toReturn.setToneRowId(toneRowId);
        toReturn.setWorkId(workId);

        Note[] retrogradeNoteOrder = new Note[12];
        for (int i = 0; i < noteOrder.length/2; i++) {
            Note temp = noteOrder[i];
            retrogradeNoteOrder[i] = noteOrder[noteOrder.length - i - 1];
            retrogradeNoteOrder[retrogradeNoteOrder.length - i -1] = temp;
        }
        toReturn.setNoteOrder(retrogradeNoteOrder);

        return toReturn;
    }
}
