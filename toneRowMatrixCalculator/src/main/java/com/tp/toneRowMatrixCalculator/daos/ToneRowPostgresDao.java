package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.Note;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import com.tp.toneRowMatrixCalculator.daos.mappers.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Profile({"production","daoTesting"})
public class ToneRowPostgresDao implements ToneRowDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Map<Integer, Matrix> getAllMatrices() {
        List<ToneRow> toneRowList = template.query(
                "SELECT \"toneRowId\",\"workId\" FROM \"toneRows\";",
                new ToneRowMapper());

        for (ToneRow toSet : toneRowList) {
            setNoteOrderForToneRow(toSet);
        }

        Map<Integer, Matrix> toReturn = new HashMap<>();

        for (ToneRow toMap : toneRowList) {
            toReturn.put(toMap.getToneRowId(), toMap.generateMatrix());
        }

        return toReturn;
    }

    private void setNoteOrderForToneRow(ToneRow toSet) {
        List<Note> noteList = template.query(
                "SELECT * FROM \"notes\" AS \"n\"\n" +
                "WHERE \"n\".\"toneRowId\" = ?;",
                new NoteMapper(),
                toSet.getToneRowId());

        Note[] orderedNotes = new Note[12];
        for (Note toOrder : noteList) {
            orderedNotes[toOrder.getOrderIndex()] = toOrder;
        }

        toSet.setNoteOrder(orderedNotes);
    }

    @Override
    public ToneRow createToneRow(Integer workId) {
        return template.queryForObject("INSERT INTO \"toneRows\" (\"workId\") " +
                        "VALUES (?) RETURNING \"toneRowId\",\"workId\";",
                new ToneRowMapper(),
                workId);
    }

    private static class ToneRowMapper implements RowMapper<ToneRow> {
        @Override
        public ToneRow mapRow(ResultSet resultSet, int i) throws SQLException {
            ToneRow mappedToneRow = new ToneRow();
            mappedToneRow.setToneRowId(resultSet.getInt("toneRowId"));
            mappedToneRow.setWorkId(resultSet.getInt("workId"));
            return mappedToneRow;
        }
    }


}
