package com.tp.toneRowMatrixCalculator.models;

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
        throw new UnsupportedOperationException();
    }
}
