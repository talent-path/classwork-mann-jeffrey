package com.tp.library.services;

import com.tp.library.models.Book;
import com.tp.library.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibraryServiceTest {
    @Autowired
    LibraryService service;

    @BeforeEach
    public void setup() throws InvalidBookIdException, NullArgumentException {
        List<Book> allBooks = service.getAllBooks();

        for (Book toDelete : allBooks) {
            service.deleteBook(toDelete.getId());
        }
    }

    @Test
    public void createBookTestGoldenPath() {
        // createBook( String title, String[] authors, Integer publicationDate );
        try {
            List<String> firstAuthors = Stream.of("test1", "test2").collect(Collectors.toList());
            Book firstTestBook = service.createBook("Test", firstAuthors, 1993);

            assertEquals(1, firstTestBook.getId());
            assertEquals("test1", firstTestBook.getAuthors().get(0));
            assertNotNull(firstTestBook.getAuthors());
            assertFalse(firstTestBook.getAuthors().isEmpty());
            assertEquals(1993, firstTestBook.getPublicationYear());

            List<String> secondAuthors = Stream.of("test3", "test4").collect(Collectors.toList());
            Book secondTestBook = service.createBook("Test 2", secondAuthors, 1986);

            assertEquals(2, secondTestBook.getId());
            assertEquals("test3", secondTestBook.getAuthors().get(0));
            assertNotNull(secondTestBook.getAuthors());
            assertFalse(secondTestBook.getAuthors().isEmpty());
            assertEquals(1986, secondTestBook.getPublicationYear());
        } catch (NullArgumentException | InvalidTitleException
                | InvalidAuthorsException | InvalidPublicationYearException
                | InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void createBookTestNullTitle() {
        List<String> firstAuthors = Stream.of("test1", "test2").collect(Collectors.toList());
        Exception e = assertThrows(NullArgumentException.class, () -> {
            service.createBook(null, firstAuthors, 1993);
        });

        String expectedMsg = "Tried to create book with Null title";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void createBookTestNullAuthors() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            service.createBook("Test", null, 1993);
        });

        String expectedMsg = "Tried to create book with Null authors list";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void createBookTestNullPublicationYear() {
        List<String> firstAuthors = Stream.of("test1", "test2").collect(Collectors.toList());
        Exception e = assertThrows(NullArgumentException.class, () -> {
            service.createBook("Test", firstAuthors, null);
        });

        String expectedMsg = "Tried to create book with Null publication year";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void createBookTestEmptyTitle() {
        List<String> firstAuthors = Stream.of("test1", "test2").collect(Collectors.toList());
        Exception e = assertThrows(InvalidTitleException.class, () -> {
            service.createBook("", firstAuthors, 1993);
        });

        String expectedMsg = "Tried to create book with empty title";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void createBookTestEmptyAuthors() {
        List<String> firstAuthors = new ArrayList<>();
        Exception e = assertThrows(InvalidAuthorsException.class, () -> {
            service.createBook("Test", firstAuthors, 1993);
        });

        String expectedMsg = "Tried to create book with empty authors list";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void createBookTestBlankAuthorInAuthors() {
        // Arrange
        List<String> firstAuthors = Stream.of("test", "test1", "").collect(Collectors.toList());
        // Assert
        Exception e1 = assertThrows(InvalidAuthorsException.class, () -> {
            //Act
            service.createBook("Test", firstAuthors, 1993);
        });

        // Arrange
        List<String> secondAuthors = Stream.of("test", "test1", "test3").collect(Collectors.toList());
        secondAuthors.add("");
        // Assert
        Exception e2 = assertThrows(InvalidAuthorsException.class, () -> {
            //Act
            service.createBook("Test", secondAuthors, 1993);
        });


        String expectedMsg = "Tried to create book with empty author in authors list";
        String actualMsg1 = e1.getMessage();
        String actualMsg2 = e2.getMessage();

        assertEquals(expectedMsg, actualMsg1);
        assertEquals(expectedMsg, actualMsg2);
    }

    @Test
    public void createBookTestNullAuthorInAuthors() {
        // Arrange
        List<String> firstAuthors = Stream.of("test", "test1", null).collect(Collectors.toList());
        // Assert
        Exception e1 = assertThrows(InvalidAuthorsException.class, () -> {
            //Act
            service.createBook("Test", firstAuthors, 1993);
        });

        // Arrange
        List<String> secondAuthors = Stream.of("test", "test1", "test3").collect(Collectors.toList());
        secondAuthors.add(null);
        // Assert
        Exception e2 = assertThrows(InvalidAuthorsException.class, () -> {
            //Act
            service.createBook("Test", secondAuthors, 1993);
        });

        String expectedMsg = "Tried to create book with null author in authors list";
        String actualMsg1 = e1.getMessage();
        String actualMsg2 = e2.getMessage();

        assertEquals(expectedMsg, actualMsg1);
        assertEquals(expectedMsg, actualMsg2);
    }

    @Test
    public void createBookTestYearOutOfRangeMin() {
        List<String> authors = Stream.of("test1", "test2").collect(Collectors.toList());
        Exception e = assertThrows(InvalidPublicationYearException.class, () -> {
            service.createBook("Test", authors, -1);
        });


        String expectedMessage = "Tried to create book with publication year out of range";
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void createBookTestYearOutOfRangeMax() {
        List<String> authors = Stream.of("test1", "test2").collect(Collectors.toList());
        Exception e = assertThrows(InvalidPublicationYearException.class, () -> {
            service.createBook("Test", authors, Integer.MAX_VALUE);
        });

        String expectedMessage = "Tried to create book with publication year out of range";
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getAllBooksTestGoldenPath()
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Book book1 = service.createBook("Test", authors, 1998);
        Book book2 = service.createBook("Test 2", authors, 1988);
        List<Book> allBooks = service.getAllBooks();

        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(book1.getId(), allBooks.get(0).getId());
        assertEquals(book2.getId(), allBooks.get(1).getId());
    }

    @Test
    public void getAllBooksByTitleTestGoldenPath()
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        String testTitle = "Test";
        List<String> authors1 = Stream.of("test1", "test2").collect(Collectors.toList());
        List<String> authors2 = Stream.of("test3", "test4").collect(Collectors.toList());
        Book testBook1 = service.createBook(testTitle, authors1, 1990);
        Book testBook2 = service.createBook(testTitle, authors2, 1980);
        try {
            List<Book> booksByTitle = service.getAllBooksByTitle(testTitle);

            assertNotNull(booksByTitle);
            assertFalse(booksByTitle.isEmpty());
            assertEquals(testBook1.getId(), booksByTitle.get(0).getId());
            assertEquals(testTitle, booksByTitle.get(0).getTitle());
            assertEquals(testBook2.getId(), booksByTitle.get(1).getId());
            assertEquals(testTitle, booksByTitle.get(1).getTitle());
        } catch (NullArgumentException | InvalidTitleException ex) {
            fail();
        }
    }

    @Test
    public void getAllBooksByTitleTestNullTitle() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            service.getAllBooksByTitle(null);
        });

        String expectedMsg = "Tried to find books with Null title";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksByTitleTestEmptyTitle() {
        Exception e = assertThrows(InvalidTitleException.class, () -> {
            service.getAllBooksByTitle("");
        });

        String expectedMsg = "Tried to find books with empty title";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }


    @Test
    public void getAllBooksByAuthorTestGoldenPath()
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        String testAuthor = "test";
        List<String> authors = Stream.of("test", "test1", "test2").collect(Collectors.toList());
        List<String> differentAuthors = Stream.of("test3", "test4", "test5").collect(Collectors.toList());
        Book testBook1 = service.createBook("Test", authors, 1990);
        Book testBook2 = service.createBook("Test 2", authors, 1980);
        Book testBook3 = service.createBook("Test 3", differentAuthors, 1970);

        try {
            List<Book> booksByAuthor = service.getAllBooksByAuthor(testAuthor);
            List<Integer> ids = booksByAuthor.stream().map(Book::getId).collect(Collectors.toList());

            assertFalse(booksByAuthor.isEmpty());

            assertTrue(ids.contains(testBook1.getId()));
            assertEquals(testBook1.getAuthors().get(0), booksByAuthor.get(0).getAuthors().get(0));
            assertEquals(testAuthor, booksByAuthor.get(0).getAuthors().get(0));

            assertTrue(ids.contains(testBook2.getId()));
            assertEquals(testBook2.getAuthors().get(0), booksByAuthor.get(1).getAuthors().get(0));
            assertEquals(testAuthor, booksByAuthor.get(1).getAuthors().get(0));

            assertFalse(ids.contains(testBook3.getId()));
        } catch (NullArgumentException | InvalidAuthorsException ex) {
            fail();
        }
    }

    @Test
    public void getAllBooksByAuthorTestNullAuthor() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            service.getAllBooksByAuthor(null);
        });

        String expectedMsg = "Tried to find books with Null author";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksByAuthorTestEmptyAuthor() {
        Exception e = assertThrows(InvalidAuthorsException.class, () -> {
            service.getAllBooksByAuthor("");
        });

        String expectedMsg = "Tried to find books with empty author";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksByPublicationYearTestGoldenPath()
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        Integer testYear = 1990;
        List<String> authors = Stream.of("test", "test1", "test2").collect(Collectors.toList());
        Book testBook1 = service.createBook("Test", authors, testYear);
        Book testBook2 = service.createBook("Test 2", authors, testYear);
        Book testBook3 = service.createBook("Test 3", authors, 1985);

        try {
            List<Book> booksByPublicationYear = service.getAllBooksByPublicationYear(testYear);
            List<Integer> ids = booksByPublicationYear.stream().map(Book::getId).collect(Collectors.toList());

            assertFalse(booksByPublicationYear.isEmpty());

            assertTrue(ids.contains(testBook1.getId()));
            assertEquals(testBook1.getPublicationYear(), booksByPublicationYear.get(0).getPublicationYear());
            assertEquals(testYear, booksByPublicationYear.get(0).getPublicationYear());

            assertTrue(ids.contains(testBook2.getId()));
            assertEquals(testBook2.getPublicationYear(), booksByPublicationYear.get(1).getPublicationYear());
            assertEquals(testYear, booksByPublicationYear.get(1).getPublicationYear());

            assertFalse(ids.contains(testBook3.getId()));
        } catch (NullArgumentException | InvalidPublicationYearException ex) {
            fail();
        }
    }

    @Test
    public void getAllBooksByPublicationYearTestNullYear() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            service.getAllBooksByPublicationYear(null);
        });

        String expectedMsg = "Tried to find books with Null publication year";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksByPublicationYearTestOutofRangeMin() {
        Exception e = assertThrows(InvalidPublicationYearException.class, () -> {
            service.getAllBooksByPublicationYear(-1);
        });

        String expectedMsg = "Tried to find books with publication year out of range";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getAllBooksByPublicationYearTestOutofRangeMax() {
        Exception e = assertThrows(InvalidPublicationYearException.class, () -> {
            service.getAllBooksByPublicationYear(Integer.MAX_VALUE);
        });

        String expectedMsg = "Tried to find books with publication year out of range";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getBookByIdTestGoldenPath()
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        List<String> authors = Stream.of("test 1", "test 2", "test 3").collect(Collectors.toList());
        Book testBook = service.createBook("Test", authors, 1990);
        try {
            Book toTest = service.getBookById(1);

            assertEquals(testBook.getId(), toTest.getId());
        } catch (NullArgumentException | InvalidBookIdException e) {
            fail();
        }
    }

    @Test
    public void getBookByIdTestNullId() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            Book toTest = service.getBookById(null);
        });

        String expectedMsg = "Tried to find book by Null id";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void getBookByIdTestInvalidId() {
        Exception e = assertThrows(InvalidBookIdException.class, () -> {
            Book toTest = service.getBookById(Integer.MAX_VALUE);
        });

        String expectedMsg = "The book with id " + Integer.MAX_VALUE + " does not exist";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void deleteBookByIdTestGoldenPath()
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        List<String> authors = Stream.of("test1", "test2").collect(Collectors.toList());
        Book testBook = service.createBook("Test", authors, 1990);
        try {
            int deletedId = service.deleteBook(testBook.getId());

            assertThrows(InvalidBookIdException.class, () -> service.getBookById(deletedId));
        } catch (InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void deleteBookByIdTestNullId() {
        Exception e = assertThrows(NullArgumentException.class, () -> {
            service.deleteBook(null);
        });

        String expectedMsg = "Tried to delete book with Null id";
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void deleteBookByIdTestInvalidId() {
        Exception e = assertThrows(InvalidBookIdException.class, () -> {
            service.deleteBook(Integer.MAX_VALUE);
        });

        String expectedMsg = "No book with the id: " + Integer.MAX_VALUE;
        String actualMsg = e.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void editBookTestGoldenPath()
            throws InvalidPublicationYearException, NullArgumentException,
            InvalidBookIdException, InvalidTitleException, InvalidAuthorsException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Book testBook = service.createBook("Test", authors, 1990);
        try {
            authors.add("test4");
            Book edited = service.editBook(testBook.getId(), "Test 1000", authors, 1900);

            assertEquals("Test 1000", edited.getTitle());
            assertEquals("test4", edited.getAuthors().get(3));
            assertEquals(1900, edited.getPublicationYear());
            assertEquals(testBook.getId(), edited.getId());
        } catch (NullArgumentException | InvalidTitleException
                | InvalidAuthorsException | InvalidPublicationYearException
                | InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void editBookTestNullTitle()
            throws InvalidPublicationYearException, NullArgumentException,
            InvalidBookIdException, InvalidTitleException, InvalidAuthorsException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Book testBook = service.createBook("Test", authors, 1990);
        try {
            authors.add("test4");
            Book edited = service.editBook(testBook.getId(), null, authors, 1900);

            assertEquals(testBook.getTitle(), edited.getTitle());
            assertEquals("test4", edited.getAuthors().get(3));
            assertEquals(1900, edited.getPublicationYear());
            assertEquals(testBook.getId(), edited.getId());
        } catch (NullArgumentException | InvalidTitleException
                | InvalidAuthorsException | InvalidPublicationYearException
                | InvalidBookIdException ex) {
            fail();
        }
    }

    @Test
    public void editBookTestNullAuthors()
            throws InvalidPublicationYearException, NullArgumentException,
            InvalidBookIdException, InvalidTitleException, InvalidAuthorsException {
        List<String> authors = Stream.of("test1", "test2", "test3").collect(Collectors.toList());
        Book testBook = service.createBook("Test", authors, 1990);
        try {
            authors.add("test4");
            Book edited = service.editBook(testBook.getId(), "Test 1000", null, 1900);

            assertEquals("Test 1000", edited.getTitle());
            assertEquals("test4", edited.getAuthors().get(3));
            assertEquals(1900, edited.getPublicationYear());
            assertEquals(testBook.getId(), edited.getId());
        } catch (NullArgumentException | InvalidTitleException
                | InvalidAuthorsException | InvalidPublicationYearException
                | InvalidBookIdException ex) {
            fail();
        }
    }
}
