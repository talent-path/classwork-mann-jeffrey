package com.tp.toneRowMatrixCalculator.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class ToneRowPostgresDaoTest {
    @Autowired
    ToneRowPostgresDao toTest;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    public void setup() {
        // 1. TRUNCATE tables
        template.update("TRUNCATE \"toneRowNotes\", \"notes\", \"toneRows\", \"composerWorks\", \"works\", \"composers\" RESTART IDENTITY;");
        // 2.
    }

}
