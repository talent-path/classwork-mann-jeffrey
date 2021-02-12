package com.tp.toneRowMatrixCalculator.persistence;

import com.tp.toneRowMatrixCalculator.models.Note;
import com.tp.toneRowMatrixCalculator.persistence.mappers.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotePostgresDao implements NoteDao {
    @Autowired
    JdbcTemplate template;

    @Override
    public Note createNote(int pitchClass, int orderIndex, Integer toneRowId) {
        return template.queryForObject("INSERT INTO \"notes\" (\"pitchClass\", \"noteOrder\", \"toneRowId\")\n" +
                        "VALUES (?,?,?) " +
                        "RETURNING \"noteId\",\"pitchClass\",\"noteOrder\",\"toneRowId\"",
                new NoteMapper(),
                pitchClass, orderIndex, toneRowId);
    }
}
