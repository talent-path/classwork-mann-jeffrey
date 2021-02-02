package com.tp.library.services;

import com.tp.library.exceptions.*;
import com.tp.library.models.Book;
import com.tp.library.persistence.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    LibraryDao dao;

    public Book createBook(String title, List<String> authors, Integer publicationYear)
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        //no null args
        if (title == null) {
            throw new NullArgumentException("Tried to create book with Null title");
        }
        if (authors == null) {
            throw new NullArgumentException("Tried to create book with Null authors list");
        }
        if (publicationYear == null) {
            throw new NullArgumentException("Tried to create book with Null publication year");
        }

        //check valid args
        if (title.equals("")) {
            throw new InvalidTitleException("Tried to create book with empty title");
        }
        if (title.trim().equals("")) {
            throw new InvalidTitleException("Tried to create book with blank title");
        }
        if (authors.isEmpty()) {
            throw new InvalidAuthorsException("Tried to create book with empty authors list");
        }
        if (authors.contains(null)) {
            throw new InvalidAuthorsException("Tried to create book with null author in authors list");
        }
        if (authors.contains("")) {
            throw new InvalidAuthorsException("Tried to create book with empty author in authors list");
        }

        // Check for a blank author with regex!
        boolean blanks = false;
        for (String a : authors) {
            blanks = Pattern.compile("\\A\\s*\\z").matcher(a).matches();
        }
        if (blanks) {
            throw new InvalidAuthorsException("Tried to create book with blank author in authors list");
        }

        if (publicationYear < 0 || publicationYear > 2500) {
            throw new InvalidPublicationYearException("Tried to create book with publication year out of range");
        }

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
        if (id == null) throw new NullArgumentException("Tried to find book by Null id");

        List<Integer> ids = dao.getAllBooks().stream().map(Book::getId).collect(Collectors.toList());
        if (!ids.contains(id)) throw new InvalidBookIdException("The book with id " + id + " does not exist");

        return dao.getBookById(id);
    }

    public List<Book> getAllBooksByTitle(String title) throws NullArgumentException, InvalidTitleException {
        if (title == null) throw new NullArgumentException("Tried to find books with Null title");
        if (title.equals("")) throw new InvalidTitleException("Tried to find books with empty title");

        return dao.getAllBooksByTitle(title);
    }

    public List<Book> getAllBooksByAuthor(String author)
            throws NullArgumentException, InvalidAuthorsException {
        if (author == null) throw new NullArgumentException("Tried to find books with Null author");
        if (author.equals("")) throw new InvalidAuthorsException("Tried to find books with empty author");

        // Check for a blank author with regex!
        boolean blanks = Pattern.compile("\\A\\s*\\z").matcher(author).matches();
        if (blanks) {
            throw new InvalidAuthorsException("Tried to find books with blank author");
        }

        return dao.getAllBooksByAuthor(author);
    }

    public List<Book> getAllBooksByPublicationYear(Integer year)
            throws NullArgumentException, InvalidPublicationYearException {
        if (year == null) throw new NullArgumentException("Tried to find books with Null publication year");

        if (year < 0 || year > 2500) {
            throw new InvalidPublicationYearException("Tried to find books with publication year out of range");
        }

        return dao.getAllBooksByPublicationYear(year);
    }

    public Book editBook(Integer id, String title, List<String> authors, Integer publicationYear)
            throws InvalidBookIdException, NullArgumentException,
            InvalidTitleException, InvalidPublicationYearException, InvalidAuthorsException {
        if (id == null) throw new NullArgumentException("Tried to edit book with Null id");
        if (title == null) title = dao.getBookById(id).getTitle();
        if (authors == null) authors = dao.getBookById(id).getAuthors();
        if (publicationYear == null) publicationYear = dao.getBookById(id).getPublicationYear();

        if (title == null) throw new NullArgumentException("Tried to edit book with Null title");
        if (authors == null) throw new NullArgumentException("Tried to edit book with Null authors list");
        if (publicationYear == null) throw new NullArgumentException("Tried to edit book with Null publication year");

        if (title.equals("")) throw new InvalidTitleException("Tried to edit title with an empty string");
        if (authors.isEmpty()) throw new InvalidAuthorsException("Tried to edit authors with an empty list");
        if (authors.contains("")) throw new InvalidAuthorsException("Tried to edit authors with an empty string");
        if (authors.contains(null)) throw new InvalidAuthorsException("Tried to edit authors with a Null author");

        // Check for a blank author with regex!
        boolean blanks = false;
        for (String a : authors) {
            blanks = Pattern.compile("\\A\\s*\\z").matcher(a).matches();
        }
        if (blanks) {
            throw new InvalidAuthorsException("Tried to edit authors with a blank author");
        }

        if (publicationYear < 0) throw new InvalidPublicationYearException("Cannot change title to an empty string");
        if (publicationYear > 2500) throw new InvalidPublicationYearException("Cannot change title to an empty string");

        Book toEdit = dao.getBookById(id);
        toEdit.setTitle(title);
        toEdit.setAuthors(authors);
        toEdit.setPublicationYear(publicationYear);

        dao.updateBook(toEdit);

        return getBookById(id);
    }
}
