package com.tp.toneRowMatrixCalculator.persistence;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.Note;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ToneRowPostgresDao implements ToneRowDao {
    @Autowired
    JdbcTemplate template;

    @Override
    public List<ToneRow> getAllMatrices() {
        List<ToneRow> toneRowList = template.query(
                "SELECT \"toneRowId\" FROM \"toneRows\";",
                new ToneRowMapper());

        for (ToneRow toSet : toneRowList) {
            setNoteOrderForToneRow(toSet);
        }

        //TODO: create generate matrix method on ToneRow
//        Map<Integer, Matrix> toReturn = new HashMap<>();
//
//        for (ToneRow toMap : toneRowList) {
//            toReturn.put(toMap.getToneRowId(), toMap.generateMatrix());
//        }

        return toneRowList;
    }

    private void setNoteOrderForToneRow(ToneRow toSet) {
        List<Note> noteList = template.query(
                "SELECT * FROM \"toneRowNotes\" AS \"trn\"\n" +
                "LEFT JOIN \"notes\" AS \"n\" ON \"trn\".\"noteId\" = \"n\".\"noteId\"\n" +
                "WHERE \"trn\".\"toneRowId\" = ?;",
                new NoteMapper(),
                toSet.getToneRowId());

        Note[] orderedNotes = new Note[12];
        for (Note toOrder : noteList) {
            orderedNotes[toOrder.getOrderIndex()] = toOrder;
        }

        toSet.setNoteOrder(orderedNotes);
    }

    private class ToneRowMapper implements RowMapper<ToneRow> {
        public ToneRow mapRow(ResultSet resultSet, int i) throws SQLException {
            ToneRow mappedToneRow = new ToneRow();
            mappedToneRow.setToneRowId(resultSet.getInt("toneRowId"));
            return mappedToneRow;
        }
    }

    private class NoteMapper implements RowMapper<Note> {

        @Override
        public Note mapRow(ResultSet resultSet, int i) throws SQLException {
            Note mappedNote = new Note();
            mappedNote.setNoteId(resultSet.getInt("noteId"));
            mappedNote.setValue(resultSet.getInt("value"));
            mappedNote.setAccidental(resultSet.getBoolean("accidental"));
            if (mappedNote.isAccidental()) {
                mappedNote.setSharpName(resultSet.getString("sharpName"));
                mappedNote.setFlatName(resultSet.getString("flatName"));
            } else {
                mappedNote.setNaturalName(resultSet.getString("naturalName"));
            }
            mappedNote.setOrderIndex((resultSet.getInt("noteOrder") - 1) % 12);

            return mappedNote;
        }
    }
}
