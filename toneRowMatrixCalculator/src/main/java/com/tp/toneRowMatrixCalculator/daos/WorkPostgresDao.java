package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WorkPostgresDao implements WorkDao{
    @Autowired
    JdbcTemplate template;

    @Override
    public Work createWork(String workTitle) {
        return template.queryForObject(
                "INSERT INTO \"works\" (\"title\") VALUES (?) RETURNING \"workId\"",
                new WorkMapper(),
                workTitle
        );
    }

    @Override
    public Work getWorkByTitle(String workTitle) {
        List<Work> results = template.query(
                "SELECT \"workId\" \"title\" FROM \"works\" wo WHERE wo.\"title\" = ?;",
                new WorkMapper(),
                workTitle
        );

        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean exists(String workTitle) {
        return getWorkByTitle(workTitle) != null;
    }

    private static class WorkMapper implements RowMapper<Work> {

        @Override
        public Work mapRow(ResultSet resultSet, int i) throws SQLException {
            Work mappedWork = new Work();
            mappedWork.setWorkId(resultSet.getInt("workId"));
            mappedWork.setTitle( resultSet.getString("title"));
            return mappedWork;
        }
    }

}
