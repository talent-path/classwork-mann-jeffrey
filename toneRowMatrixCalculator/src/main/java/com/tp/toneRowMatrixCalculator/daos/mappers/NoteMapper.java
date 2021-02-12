package com.tp.toneRowMatrixCalculator.daos.mappers;

import com.tp.toneRowMatrixCalculator.models.Note;
import com.tp.toneRowMatrixCalculator.models.NoteInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet resultSet, int i) throws SQLException {
        Note mappedNote = new Note();
        NoteInfo mappedInfo = NoteInfo.getByValue(resultSet.getInt("pitchClass"));

        mappedNote.setNoteId(resultSet.getInt("noteId"));
        mappedNote.setNoteInfo(mappedInfo);
        mappedNote.setOrderIndex(resultSet.getInt("noteOrder"));

        return mappedNote;
    }
}
