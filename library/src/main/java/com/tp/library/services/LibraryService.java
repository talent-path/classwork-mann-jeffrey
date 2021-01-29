package com.tp.library.services;

import com.tp.library.exceptions.*;
import com.tp.library.models.Book;
import com.tp.library.persistence.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class LibraryService {
    @Autowired
    LibraryDao dao;


    public Book createBook(String title, List<String> authors, Integer publicationYear)
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        int id = dao.createBook(title, authors, publicationYear);
        return dao.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    public Integer deleteBook(Integer id) throws InvalidBookIdException, NullArgumentException {
        return dao.deleteBook(id);
    }

    public Book getBookById(Integer id) throws InvalidBookIdException, NullArgumentException {
        return dao.getBookById(id);
    }

    public List<Book> getAllBooksByTitle(String title) throws NullArgumentException, InvalidTitleException {
        return dao.getAllBooksByTitle(title);
    }

    public List<Book> getAllBooksByAuthor(String author) throws NullArgumentException, InvalidAuthorsException{
        return dao.getAllBooksByAuthor(author);
    }

    public List<Book> getAllBooksByPublicationYear(Integer year) throws NullArgumentException, InvalidPublicationYearException {
        return dao.getAllBooksByPublicationYear(year);
    }
}
