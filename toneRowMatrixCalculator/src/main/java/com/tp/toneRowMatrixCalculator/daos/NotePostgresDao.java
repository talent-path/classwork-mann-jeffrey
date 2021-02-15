package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Note;
import com.tp.toneRowMatrixCalculator.daos.mappers.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Note> getNotesForToneRow(Integer toneRowId) {
        return template.query("SELECT \"noteId\", \"toneRowId\", \"pitchClass\", \"noteOrder\"\n" +
                "FROM \"notes\"\n" +
                "WHERE \"toneRowId\" = ?;",
                new NoteMapper(),
                toneRowId);
    }
}
