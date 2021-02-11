package com.tp.toneRowMatrixCalculator.models;

import java.util.Optional;

public class Note {
    private Integer noteId;
    private Integer orderIndex;
    private NoteInfo noteInfo;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getValue() {
        return noteInfo.value;
    }

    public String getNaturalName() {
        return noteInfo.naturalName;
    }

    public String getFlatName() {
        return noteInfo.flatName;
    }

    public String getSharpName() {
        return noteInfo.sharpName;
    }

    public boolean isAccidental() {
        return noteInfo.accidental;
    }

    public NoteInfo getNoteInfo() {
        return noteInfo;
    }

    public void setNoteInfo(NoteInfo noteInfo) {
        this.noteInfo = noteInfo;
    }

    public Note transpose(int transpositionValue) {
        int thisValue = getValue();
        NoteInfo transposedInfo = NoteInfo.getByValue(
                (getValue() + transpositionValue + 12) % 12
        );
        Note transposed = new Note();
        transposed.setNoteId(noteId);
        transposed.setOrderIndex(orderIndex);
        transposed.setNoteInfo(transposedInfo);

        return transposed;
    }

    public int interval(Note comparison) {
        return comparison.getValue() - getValue();
    }
}
