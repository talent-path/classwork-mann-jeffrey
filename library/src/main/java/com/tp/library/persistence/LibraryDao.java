package com.tp.library.persistence;

import com.tp.library.exceptions.*;
import com.tp.library.models.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryDao {

    Integer createBook(String title, List<String> authors, Integer publicationYear)
            throws NullArgumentException, InvalidTitleException, InvalidAuthorsException, InvalidPublicationYearException;

    Book getBookById(Integer id) throws InvalidBookIdException, NullArgumentException;

    List<Book> getAllBooks();

    Integer deleteBook(Integer id) throws InvalidBookIdException, NullArgumentException;

    List<Book> getAllBooksByTitle(String title) throws NullArgumentException, InvalidTitleException;

    List<Book> getAllBooksByAuthor(String author) throws NullArgumentException, InvalidAuthorsException;

    List<Book> getAllBooksByPublicationYear(Integer year) throws NullArgumentException, InvalidPublicationYearException;
}
