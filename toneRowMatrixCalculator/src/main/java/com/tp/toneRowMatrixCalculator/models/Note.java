package com.tp.toneRowMatrixCalculator.models;

public class Note {
    private Integer noteId;
    private Integer value;
    private Integer orderIndex;
    private String naturalName;
    private String flatName;
    private String sharpName;
    private boolean accidental;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNaturalName() {
        return naturalName;
    }

    public void setNaturalName(String naturalName) {
        this.naturalName = naturalName;
    }

    public String getFlatName() {
        return flatName;
    }

    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    public String getSharpName() {
        return sharpName;
    }

    public void setSharpName(String sharpName) {
        this.sharpName = sharpName;
    }

    public boolean isAccidental() {
        return accidental;
    }

    public void setAccidental(boolean accidental) {
        this.accidental = accidental;
    }



}
