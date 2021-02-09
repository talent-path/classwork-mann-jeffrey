package com.tp.library.persistence;

import com.tp.library.exceptions.*;
import com.tp.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostgresLibraryDao implements LibraryDao {

    @Autowired
    JdbcTemplate template;

    private Integer addAuthor(String author) {
        return template.query("INSERT INTO \"authors\" (\"name\")\n" +
                "VALUES ('" + author + "')\n" +
                "RETURNING \"authors\".\"id\";", new IdMapper()).get(0);
    }

    @Override
    public Integer createBook(String title, List<String> authors, Integer publicationYear)
            throws NullArgumentException {
        List<Integer> authorList = new ArrayList<>();
        for (String author :
                authors) {
            Integer authorId = addOrRetrieve(author);
            authorList.add(authorId);
        }
        return null;
    }

    private Integer addOrRetrieve(String author) {
        Integer authorId = getAuthorId(author);
        if (authorId == null) {
            authorId = addAuthor(author);
        }
        return authorId;
    }

    private Integer getAuthorId(String author) {
        List<Integer> authorId = template.query("SELECT \"authors\".\"id\"\n" +
                "FROM \"authors\"\n" +
                "WHERE \"name\" = '" + author + "';", new IdMapper());

        if (authorId.isEmpty()) {
            return null;
        } else {
            return authorId.get(0);
        }
    }

    @Override
    public Book getBookById(Integer id) throws InvalidBookIdException, NullArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> getAllBooks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer deleteBook(Integer id) throws InvalidBookIdException, NullArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> getAllBooksByTitle(String title) throws NullArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) throws NullArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> getAllBooksByPublicationYear(Integer year) throws NullArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Book editBook(Integer id, String title, List<String> authors, Integer publicationYear) throws NullArgumentException, InvalidTitleException, InvalidAuthorsException, InvalidPublicationYearException, InvalidBookIdException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateBook(Book toEdit) {

    }

    private class IdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("id");
        }
    }
}
