package com.tp.library.persistence;

import com.tp.library.exceptions.InvalidBookIdException;
import com.tp.library.exceptions.NullArgumentException;
import com.tp.library.models.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class LibraryInMemDao implements LibraryDao {
    //    private Map<Integer, Book> allBooks = new HashMap<>();
    private final List<Book> allBooks = new ArrayList<>();

    public LibraryInMemDao() {
        List<String> authors = Stream.of("Malcolm Gladwell").collect(Collectors.toList());
        Book onlyBook = new Book(1, "Outliers", authors, 1990);
        allBooks.add(onlyBook);
    }
    
    private List<Book> bulkCopy(List<Book> copyList) {
        List<Book> toReturn = new ArrayList<>();
        for (Book toCopy : copyList) {
            toReturn.add(new Book(toCopy));
        }

        return toReturn;
    }

    @Override
    public Integer createBook(String title, List<String> authors, Integer publicationYear)
            throws NullArgumentException {
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

        //iterate ids
        int id = 0;
        for (Book toCheck : allBooks) {
            if (toCheck.getId() > id) {
                id = toCheck.getId();
            }
        }
        id++;

        Book toAdd = new Book(id, title, authors, publicationYear);
        allBooks.add(toAdd);
        return id;
    }

    @Override
    public Book getBookById(Integer id) throws NullArgumentException {
        if (id == null) throw new NullArgumentException("Tried to find book by Null id");

        Book toReturn = allBooks.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        return new Book(toReturn);
    }

    @Override
    public List<Book> getAllBooks() {
        return bulkCopy(allBooks);
    }

    @Override
    public Integer deleteBook(Integer id) throws InvalidBookIdException, NullArgumentException {
        if (id == null) throw new NullArgumentException("Tried to delete book with Null id");
        int removeIndex = -1;
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getId().equals(id)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            return allBooks.remove(removeIndex).getId();
        } else {
            throw new InvalidBookIdException(String.format("No book with the id: %d", id));
        }
    }

    @Override
    public List<Book> getAllBooksByTitle(String title) throws NullArgumentException {
        if (title == null) {
            throw new NullArgumentException("Tried to find books with Null title");
        }
        List<Book> toReturn = allBooks.stream().filter(b -> b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        return bulkCopy(toReturn);
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) throws NullArgumentException {
        if (author == null) throw new NullArgumentException("Tried to find books with Null author");

        List<Book> toReturn = allBooks.stream().filter(b ->
                b.getAuthors().stream().anyMatch(author::equalsIgnoreCase)
        ).collect(Collectors.toList());

        return bulkCopy(toReturn);
    }

    @Override
    public List<Book> getAllBooksByPublicationYear(Integer year) throws NullArgumentException {
        if (year == null) throw new NullArgumentException("Tried to find books with Null publication year");

        List<Book> toReturn = allBooks.stream().filter(b -> b.getPublicationYear().equals(year))
                .collect(Collectors.toList());
        return bulkCopy(toReturn);
    }

    @Override
    public Book editBook(Integer id, String title, List<String> authors, Integer publicationYear)
            throws NullArgumentException {
        if (id == null) throw new NullArgumentException("Tried to edit book with Null id");
        if (title == null) throw new NullArgumentException("Tried to edit book with Null title");
        if (authors == null) throw new NullArgumentException("Tried to edit book with Null authors list");
        if (publicationYear == null) throw new NullArgumentException("Tried to edit book with Null publication year");

        Book toEdit = getBookById(id);
        toEdit.setTitle(title);
        toEdit.setAuthors(authors);
        toEdit.setPublicationYear(publicationYear);

        return toEdit;
    }

    @Override
    public void updateBook(Book toUpdate) {
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getId().equals(toUpdate.getId())) {
                allBooks.set(i, new Book(toUpdate));
            }
        }
    }
}
