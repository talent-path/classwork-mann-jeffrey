package com.tp.toneRowMatrixCalculator.persistence;

import com.tp.toneRowMatrixCalculator.models.Composer;
import com.tp.toneRowMatrixCalculator.models.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ComposerPostgresDao implements ComposerDao {
    @Autowired
    JdbcTemplate template;

    @Override
    public Composer createComposer(String composer) {
        return template.queryForObject(
                "INSERT INTO \"composers\" (\"name\") VALUES (?) RETURNING \"composerId\"",
                new ComposerMapper(),
                composer
        );
    }

    @Override
    public Composer getComposerByName(String composer) {
        List<Composer> results = template.query(
                "SELECT \"composerId\" \"name\" FROM \"composers\" co WHERE co.\"name\" = ?;",
                new ComposerMapper(),
                composer
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
    public boolean exists(String composer) {
        return getComposerByName(composer) != null;
    }

    public static class ComposerMapper implements RowMapper<Composer> {

        @Override
        public Composer mapRow(ResultSet resultSet, int i) throws SQLException {
            Composer mappedWork = new Composer();
            mappedWork.setComposerId(resultSet.getInt("composerId"));
            mappedWork.setName( resultSet.getString("name"));
            return mappedWork;
        }
    }
}
