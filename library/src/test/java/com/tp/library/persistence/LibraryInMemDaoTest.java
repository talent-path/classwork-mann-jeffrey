package com.tp.library.persistence;

import com.tp.library.exceptions.*;
import com.tp.library.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class LibraryInMemDaoTest {
    @Autowired
    LibraryInMemDao toTest;

    @BeforeEach
    public void setup() throws InvalidBookIdException, NullArgumentException {
        toTest = new LibraryInMemDao();
        List<Book> allBooks = toTest.getAllBooks();
        for (Book toRemove : allBooks) {
            toTest.deleteBook(toRemove.getId());
        }
    }

    @Test
    public void createBookTestGoldenPath() {
        // createBook( String title, String[] authors, Integer publicationDate );
        try {
            List<String> firstAuthors = Stream.of("test1", "test2").collect(Collectors.toList());
            Integer firstId = toTest.createBook("Test", firstAuthors, 1993);
            Book firstValidation = toTest.getBookById(firstId);

            assertEquals(1, firstValidation.getId());
            assertEquals("test1", firstValidation.getAuthors().get(0));
            assertNotNull(firstValidation.getAuthors());
            assertFalse(firstValidation.getAuthors().isEmpty());
            assertEquals(1993, firstValidation.getPublicationYear());

            List<String> secondAuthors = Stream.of("test3", "test4").collect(Collectors.toList());
            Integer secondId = toTest.createBook("Test 2", secondAuthors, 1986);
            Book secondValidation = toTest.getBookById(secondId);

            assertEquals(2, secondValidation.getId());
            assertEquals("test3", secondValidation.getAuthors().get(0));
            assertNotNull(secondValidation.getAuthors());
            assertFalse(secondValidation.getAuthors().isEmpty());
            assertEquals(1986, secondValidation.getPublicationYear());
        } catch (NullArgumentException | InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void createBookTestNullTitle() {
        List<String> firstAuthors = Stream.of("test1", "test2").collect(Collectors.toList());
        Exception e = assertThrows(NullArgumentException.class, () -> {
            toTest.createBook(null, firstAuthors, 1993);
        });

        String expectedMsg = "Tried to create book with Null title";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void createBookTestNullAuthors() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            toTest.createBook("Test", null, 1993);
        });

        String expectedMsg = "Tried to create book with Null authors list";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void createBookTestNullPublicationYear() {
        List<String> firstAuthors = Stream.of("test1", "test2").collect(Collectors.toList());
        Exception e = assertThrows(NullArgumentException.class, () -> {
            toTest.createBook("Test", firstAuthors, null);
        });

        String expectedMsg = "Tried to create book with Null publication year";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksTestGoldenPath() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1998);
        Integer secondId = toTest.createBook("Test 2", authors, 1988);
        List<Book> allBooks = toTest.getAllBooks();

        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(2, allBooks.size());

        assertEquals(firstId, allBooks.get(0).getId());
        assertEquals("Test", allBooks.get(0).getTitle());
        assertEquals("test1", allBooks.get(0).getAuthors().get(0));
        assertEquals(1998, allBooks.get(0).getPublicationYear());

        assertEquals(secondId, allBooks.get(1).getId());
        assertEquals("Test 2", allBooks.get(1).getTitle());
        assertEquals("test2", allBooks.get(1).getAuthors().get(1));
        assertEquals(1988, allBooks.get(1).getPublicationYear());
    }

    @Test
    public void getAllBooksByTitleTestGoldenPath() throws NullArgumentException {
        String testTitle = "Test";
        List<String> authors1 = Stream.of("test1", "test2").collect(Collectors.toList());
        List<String> authors2 = Stream.of("test3", "test4").collect(Collectors.toList());
        Integer firstId = toTest.createBook(testTitle, authors1, 1990);
        Integer secondId = toTest.createBook(testTitle, authors2, 1980);
        Integer notIncludedId = toTest.createBook("Different Title", authors1, 1970);

        try {
            List<Book> booksByTitle = toTest.getAllBooksByTitle(testTitle);
            List<Integer> ids = booksByTitle.stream().map(Book::getId).collect(Collectors.toList());


            assertNotNull(booksByTitle);
            assertFalse(booksByTitle.isEmpty());
            assertEquals(2, booksByTitle.size());

            assertTrue(ids.contains(firstId));
            assertEquals(firstId, booksByTitle.get(0).getId());
            assertEquals(testTitle, booksByTitle.get(0).getTitle());
            assertEquals("test1", booksByTitle.get(0).getAuthors().get(0));
            assertEquals(1990, booksByTitle.get(0).getPublicationYear());

            assertTrue(ids.contains(secondId));
            assertEquals(secondId, booksByTitle.get(1).getId());
            assertEquals(testTitle, booksByTitle.get(1).getTitle());
            assertEquals("test3", booksByTitle.get(1).getAuthors().get(0));
            assertEquals(1980, booksByTitle.get(1).getPublicationYear());

            assertFalse(ids.contains(notIncludedId));
        } catch (NullArgumentException ex) {
            fail();
        }
    }

    @Test
    public void getAllBooksByTitleTestNullTitle() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            toTest.getAllBooksByTitle(null);
        });

        String expectedMsg = "Tried to find books with Null title";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksByAuthorTestGoldenPath() throws NullArgumentException {
        String testAuthor = "test";
        List<String> authors = Stream.of("test", "test1", "test2").collect(Collectors.toList());
        List<String> differentAuthors = Stream.of("test3", "test4", "test5").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);
        Integer secondId = toTest.createBook("Test 2", authors, 1980);
        Integer notIncludedId = toTest.createBook("Test 3", differentAuthors, 1970);

        try {
            List<Book> booksByAuthor = toTest.getAllBooksByAuthor(testAuthor);
            List<Integer> ids = booksByAuthor.stream().map(Book::getId).collect(Collectors.toList());

            assertFalse(booksByAuthor.isEmpty());

            assertTrue(ids.contains(firstId));
            assertEquals(toTest.getBookById(firstId).getAuthors().get(0), booksByAuthor.get(0).getAuthors().get(0));
            assertEquals(testAuthor, booksByAuthor.get(0).getAuthors().get(0));
            assertEquals("Test", booksByAuthor.get(0).getTitle());
            assertEquals(1990, booksByAuthor.get(0).getPublicationYear());

            assertTrue(ids.contains(secondId));
            assertEquals(toTest.getBookById(secondId).getAuthors().get(0), booksByAuthor.get(1).getAuthors().get(0));
            assertEquals(testAuthor, booksByAuthor.get(1).getAuthors().get(0));
            assertEquals("Test 2", booksByAuthor.get(1).getTitle());
            assertEquals(1980, booksByAuthor.get(1).getPublicationYear());

            assertFalse(ids.contains(notIncludedId));
        } catch (NullArgumentException | InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void getAllBooksByAuthorTestNullAuthor() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            toTest.getAllBooksByAuthor(null);
        });

        String expectedMsg = "Tried to find books with Null author";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksByPublicationYearTestGoldenPath() throws NullArgumentException {
        Integer testYear = 1990;
        List<String> authors = Stream.of("test", "test1", "test2").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, testYear);
        Integer secondId = toTest.createBook("Test 2", authors, testYear);
        Integer notIncludedId = toTest.createBook("Test 3", authors, 1985);

        try {
            List<Book> booksByPublicationYear = toTest.getAllBooksByPublicationYear(testYear);
            List<Integer> ids = booksByPublicationYear.stream().map(Book::getId).collect(Collectors.toList());

            assertFalse(booksByPublicationYear.isEmpty());

            assertTrue(ids.contains(firstId));
            assertEquals(toTest.getBookById(firstId).getPublicationYear(), booksByPublicationYear.get(0).getPublicationYear());
            assertEquals(testYear, booksByPublicationYear.get(0).getPublicationYear());
            assertEquals("Test", booksByPublicationYear.get(0).getTitle());
            assertEquals("test", booksByPublicationYear.get(0).getAuthors().get(0));

            assertTrue(ids.contains(secondId));
            assertEquals(toTest.getBookById(secondId).getPublicationYear(), booksByPublicationYear.get(1).getPublicationYear());
            assertEquals(testYear, booksByPublicationYear.get(1).getPublicationYear());
            assertEquals("Test 2", booksByPublicationYear.get(1).getTitle());
            assertEquals("test", booksByPublicationYear.get(1).getAuthors().get(0));

            assertFalse(ids.contains(notIncludedId));
        } catch (NullArgumentException | InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void getAllBooksByPublicationYearTestNullYear() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            toTest.getAllBooksByPublicationYear(null);
        });

        String expectedMsg = "Tried to find books with Null publication year";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getBookByIdTestGoldenPath() throws NullArgumentException {
        List<String> authors = Stream.of("test 1", "test 2", "test 3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);
        try {
            Book testBook = toTest.getBookById(1);

            assertEquals(testBook.getId(), firstId);
            assertEquals("Test", testBook.getTitle());
            assertEquals("test 1", testBook.getAuthors().get(0));
            assertEquals(1990, testBook.getPublicationYear());
        } catch (NullArgumentException | InvalidBookIdException e) {
            fail();
        }
    }

    @Test
    public void getBookByIdTestNullId() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            Book firstId = toTest.getBookById(null);
        });

        String expectedMsg = "Tried to find book by Null id";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void deleteBookByIdTestGoldenPath() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);
        try {
            int deletedId = toTest.deleteBook(firstId);

            assertThrows(InvalidBookIdException.class, () -> toTest.getBookById(deletedId));
        } catch (InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void deleteBookByIdTestNullId() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            toTest.deleteBook(null);
        });

        String expectedMsg = "Tried to delete book with Null id";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void deleteBookByIdTestInvalidId() {
        Exception e = assertThrows(InvalidBookIdException.class, () -> {
            toTest.deleteBook(Integer.MAX_VALUE);
        });

        String expectedMsg = "No book with the id: " + Integer.MAX_VALUE;
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void editBookTestGoldenPath() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);
        try {
            authors.add("test4");
            Book edited = toTest.editBook(firstId, "Test 1000", authors, 1900);

            assertEquals("Test 1000", edited.getTitle());
            assertEquals("test4", edited.getAuthors().get(3));
            assertEquals(1900, edited.getPublicationYear());
            assertEquals(firstId, edited.getId());
        } catch (NullArgumentException | InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void editBookTestNullId() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);

        Exception e = assertThrows(NullArgumentException.class,
                () -> toTest.editBook(null, "Test 1000", authors, 1900));
        String expectedMsg = "Tried to edit book with Null id";
        String actualMsg = e.getMessage();

        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void editBookTestInvalidId() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);

        Exception e = assertThrows(InvalidBookIdException.class,
                () -> toTest.editBook(Integer.MAX_VALUE, "Test 1000", authors, 1900));
        String expectedMsg = "No book with the id: " + Integer.MAX_VALUE;
        String actualMsg = e.getMessage();

        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void editBookTestNullTitle() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);

        Exception e = assertThrows(NullArgumentException.class,
                () -> toTest.editBook(firstId, null, authors, 1900)
        );

        String expectedMsg = "Tried to edit book with Null title";
        String actualMsg = e.getMessage();

        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void editBookTestNullAuthors() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);

        Exception e = assertThrows(NullArgumentException.class,
                () -> toTest.editBook(firstId, "Test 1000", null, 1900)
        );

        String expectedMsg = "Tried to edit book with Null authors list";
        String actualMsg = e.getMessage();

        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void editBookTestNullPublicationYear() throws NullArgumentException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Integer firstId = toTest.createBook("Test", authors, 1990);

        Exception e = assertThrows(NullArgumentException.class,
                () -> toTest.editBook(firstId, "Test 1000", authors, null)
        );

        String expectedMsg = "Tried to edit book with Null publication year";
        String actualMsg = e.getMessage();

        assertEquals(expectedMsg, actualMsg);
    }
}
