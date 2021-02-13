package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("daoTesting")
public class ToneRowPostgresDaoTest {
    @Autowired
    ToneRowPostgresDao toTest;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    public void setup() {
        // 1. TRUNCATE tables
        template.update("TRUNCATE \"notes\", \"toneRows\", \"composerWorks\", " +
                "\"works\", \"composers\" RESTART IDENTITY;\n");

        // 2. INSERT testing data
        template.update("INSERT INTO \"works\" (\"title\") VALUES ('My First Row');");
        template.update("INSERT INTO \"composers\" (\"name\") VALUES ('Jeffrey Mann');");
        template.update("INSERT INTO \"composerWorks\" (\"composerId\", \"workId\") VALUES ('1', '1');");
        template.update("INSERT INTO \"toneRows\" (\"workId\") VALUES ('1');");
        template.update("INSERT INTO \"notes\" (\"pitchClass\", \"noteOrder\", \"toneRowId\")\n" +
                "VALUES ('0', '0', '1'), ('1', '1', '1'), ('2', '2', '1'), ('3', '3', '1'),\n" +
                "('4', '4', '1'), ('5', '5', '1'), ('6', '6', '1'), ('7', '7', '1'),\n" +
                "('8', '8', '1'), ('9', '9', '1'), ('10', '10', '1'), ('11', '11', '1');");
    }

    @Test
    public void getAllMatricesTest() {
        Map<Integer, Matrix> returned = toTest.getAllMatrices();
        ToneRow prime_zero = returned.get(1).getPrimes().get("P0");
        
        assertNotNull(returned.get(1));
        assertNotNull(prime_zero);
        assertEquals(1, prime_zero.getToneRowId());
        assertEquals(0, prime_zero.getNoteOrder()[0].getPitchClass());
        assertEquals("C", prime_zero.getNoteOrder()[0].getNaturalName());
        assertNull(prime_zero.getNoteOrder()[0].getSharpName());
        assertNull(prime_zero.getNoteOrder()[0].getFlatName());
        assertFalse(prime_zero.getNoteOrder()[0].isAccidental());

        assertNull(returned.get(2));
    }

    @Test
    public void getAllMatricesPrimeLabelsMatch() {
        Map<Integer, Matrix> returned = toTest.getAllMatrices();

        Map<String, ToneRow> primes = returned.get(1).getPrimes();

        for (String key : primes.keySet()) {
            int keyValue = Integer.parseInt(key.replace("P", ""));
            int firstPitchClass = primes.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getAllMatricesInversionLabelsMatch() {
        Map<Integer, Matrix> returned = toTest.getAllMatrices();

        Map<String, ToneRow> inversions = returned.get(1).getInversions();

        for (String key : inversions.keySet()) {
            int keyValue = Integer.parseInt(key.replace("I", ""));
            int firstPitchClass = inversions.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getAllMatricesRetrogradeLabelsMatch() {
        Map<Integer, Matrix> returned = toTest.getAllMatrices();

        Map<String, ToneRow> retrogrades = returned.get(1).getRetrogrades();

        for (String key : retrogrades.keySet()) {
            int keyValue = Integer.parseInt(key.replace("R", ""));
            int firstPitchClass = retrogrades.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getAllMatricesRetrogradeInversionLabelsMatch() {
        Map<Integer, Matrix> returned = toTest.getAllMatrices();

        Map<String, ToneRow> retrogradeInversions = returned.get(1).getRetrogradeInversions();

        for (String key : retrogradeInversions.keySet()) {
            int keyValue = Integer.parseInt(key.replace("RI", ""));
            int firstPitchClass = retrogradeInversions.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getAllMatricesPrimeMatrixMatchesPitchClasses() {
        Map<Integer, Matrix> returned = toTest.getAllMatrices();
        ToneRow prime_zero = returned.get(1).getPrimes().get("P0");

        for (int i = 0; i < 12; i++) {
            assertEquals(returned.get(1).matrix[0][i], prime_zero.getNoteOrder()[i].getPitchClass());
        }
    }

    @Test
    public void getAllMatricesInvertedMatrixMatchesPitchClasses() {
        Map<Integer, Matrix> returned = toTest.getAllMatrices();
        ToneRow inverted_zero = returned.get(1).getInversions().get("I0");

        for (int i = 0; i < 12; i++) {
            assertEquals(returned.get(1).matrix[i][0], inverted_zero.getNoteOrder()[i].getPitchClass());
        }
    }

    @Test
    public void createToneRowGoldenPathTest() {
        toTest.createToneRow(1);
    }
}
