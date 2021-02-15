package com.tp.toneRowMatrixCalculator.daos;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.Note;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
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

    // getAllMatrices()
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
        assertEquals(0, returned.get(1).matrix[0][0]);
        assertEquals(1, returned.get(1).matrix[0][1]);
        assertEquals(11, returned.get(1).matrix[1][0]);

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
    
    // getMatrixById()
    @Test
    public void getMatrixByIdTest() {
        Matrix returned = toTest.getMatrixById(1);
        ToneRow prime_zero = returned.getPrimes().get("P0");

        assertNotNull(returned);
        assertNotNull(prime_zero);
        assertEquals(1, prime_zero.getToneRowId());
        assertEquals(0, prime_zero.getNoteOrder()[0].getPitchClass());
        assertEquals("C", prime_zero.getNoteOrder()[0].getNaturalName());
        assertNull(prime_zero.getNoteOrder()[0].getSharpName());
        assertNull(prime_zero.getNoteOrder()[0].getFlatName());
        assertFalse(prime_zero.getNoteOrder()[0].isAccidental());
    }

    @Test
    public void getMatrixByIdPrimeLabelsMatch() {
        Matrix returned = toTest.getMatrixById(1);

        Map<String, ToneRow> primes = returned.getPrimes();

        for (String key : primes.keySet()) {
            int keyValue = Integer.parseInt(key.replace("P", ""));
            int firstPitchClass = primes.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getMatrixByIdInversionLabelsMatch() {
        Matrix returned = toTest.getMatrixById(1);

        Map<String, ToneRow> inversions = returned.getInversions();

        for (String key : inversions.keySet()) {
            int keyValue = Integer.parseInt(key.replace("I", ""));
            int firstPitchClass = inversions.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getMatrixByIdRetrogradeLabelsMatch() {
        Matrix returned = toTest.getMatrixById(1);

        Map<String, ToneRow> retrogrades = returned.getRetrogrades();

        for (String key : retrogrades.keySet()) {
            int keyValue = Integer.parseInt(key.replace("R", ""));
            int firstPitchClass = retrogrades.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getMatrixByIdRetrogradeInversionLabelsMatch() {
        Matrix returned = toTest.getMatrixById(1);

        Map<String, ToneRow> retrogradeInversions = returned.getRetrogradeInversions();

        for (String key : retrogradeInversions.keySet()) {
            int keyValue = Integer.parseInt(key.replace("RI", ""));
            int firstPitchClass = retrogradeInversions.get(key).getNoteOrder()[0].getPitchClass();
            assertEquals(keyValue, firstPitchClass);
        }
    }

    @Test
    public void getMatrixByIdPrimeMatrixMatchesPitchClasses() {
        Matrix returned = toTest.getMatrixById(1);
        ToneRow prime_zero = returned.getPrimes().get("P0");

        for (int i = 0; i < 12; i++) {
            assertEquals(returned.matrix[0][i], prime_zero.getNoteOrder()[i].getPitchClass());
        }
    }

    @Test
    public void getMatrixByIdInvertedMatrixMatchesPitchClasses() {
        Matrix returned = toTest.getMatrixById(1);
        ToneRow inverted_zero = returned.getInversions().get("I0");

        for (int i = 0; i < 12; i++) {
            assertEquals(returned.matrix[i][0], inverted_zero.getNoteOrder()[i].getPitchClass());
        }
    }
    
    // getAllToneRows()
    @Test
    public void getAllToneRowsTest() {
        Map<Integer, ToneRow> returned = toTest.getAllToneRows();
        Note[] notes = returned.get(1).noteOrder;

        assertNotNull(returned);
        assertEquals(1, returned.size());
        assertEquals(1, returned.get(1).getToneRowId());
        assertEquals(0, notes[0].getPitchClass());
        assertEquals("C", notes[0].getNaturalName());
        assertNull(notes[0].getSharpName());
        assertNull(notes[0].getFlatName());
        assertFalse(notes[0].isAccidental());
    }
    //TODO: more get toneRow tests
    
    // getToneRowById()
    @Test
    public void getToneRowByIdTest() {
        ToneRow returned = toTest.getToneRowById(1);
        Note[] notes = returned.noteOrder;

        assertNotNull(returned);
        assertEquals(1, returned.getToneRowId());
        assertEquals(0, notes[0].getPitchClass());
        assertEquals("C", notes[0].getNaturalName());
        assertNull(notes[0].getSharpName());
        assertNull(notes[0].getFlatName());
        assertFalse(notes[0].isAccidental());
    }
    //TODO: more get toneRow tests
    
    // createToneRow()
    @Test
    public void createToneRowGoldenPathTest() {
        ToneRow returned = toTest.createToneRow(1);

        assertEquals(2, returned.getToneRowId());
        assertEquals(1, returned.getWorkId());
        assertNull(returned.generateMatrix());
        assertNull(returned.getNoteOrder());
    }
    //TODO: more create toneRow tests with bad data/arguments
    
}
