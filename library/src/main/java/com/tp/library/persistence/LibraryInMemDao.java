package com.tp.library.persistence;

import com.tp.library.exceptions.*;
import com.tp.library.models.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class LibraryInMemDao implements LibraryDao{
//    private Map<Integer, Book> allBooks = new HashMap<>();
    private final List<Book> allBooks = new ArrayList<>();

    public LibraryInMemDao() {
        List<String> authors = Stream.of("Malcolm Gladwell").collect(Collectors.toList());
        Book onlyBook = new Book(1,"Outliers", authors, 1990);
        allBooks.add(onlyBook);
    }

    @Override
    public Integer createBook(String title, List<String> authors, Integer publicationYear)
            throws NullArgumentException, InvalidTitleException,
            InvalidAuthorsException, InvalidPublicationYearException
    {
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
        if (authors.isEmpty()) {
            throw new InvalidAuthorsException("Tried to create book with empty authors list");
        }
        if (authors.contains(null)) {
            throw new InvalidAuthorsException("Tried to create book with null author in authors list");
        }
        if (authors.contains("")) {
            throw new InvalidAuthorsException("Tried to create book with empty author in authors list");
        }
        if (publicationYear < 0 || publicationYear > 2500) {
            throw new InvalidPublicationYearException("Tried to create book with publication year out of range");
        }

        //iterate ids
        int id = 0;
        for( Book toCheck : allBooks ){
            if( toCheck.getId() > id ){
                id = toCheck.getId();
            }
        }
        id++;

        Book toAdd = new Book( id, title, authors, publicationYear );
        allBooks.add( toAdd );
        return id;
    }

    @Override
    public Book getBookById(Integer id) throws InvalidBookIdException, NullArgumentException {
        if (id == null) throw new NullArgumentException("Tried to find book by Null id");

        List<Integer> ids = allBooks.stream().map(Book::getId).collect(Collectors.toList());
        if (!ids.contains(id)) throw new InvalidBookIdException("The book with id " + id + " does not exist");

//        Book toReturn = null;
//        for (Book toCheck : allBooks) {
//            if(toCheck.getId().equals(id)) {
//                toReturn = new Book(toCheck);
//                break;
//            }
//        }
//        return toReturn;

        return allBooks.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(allBooks);
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
            throw new InvalidBookIdException(String.format("No book with the id: %d",id));
        }
    }

    @Override
    public List<Book> getAllBooksByTitle(String title) throws NullArgumentException, InvalidTitleException {
        if (title == null) {
            throw new NullArgumentException("Tried to find books with Null title");
        }
        if (title.equals("")) {
            throw new InvalidTitleException("Tried to find books with empty title");
        }
        return allBooks.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) throws NullArgumentException, InvalidAuthorsException {
        if (author == null) throw new NullArgumentException("Tried to find books with Null author");
        if (author.equals("")) throw new InvalidAuthorsException("Tried to find books with empty author");
        return allBooks.stream().filter(b ->
                b.getAuthors().stream().anyMatch(author::equalsIgnoreCase)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooksByPublicationYear(Integer year) throws NullArgumentException, InvalidPublicationYearException {
        if (year == null) throw new  NullArgumentException("Tried to find books with Null publication year");
        if (year < 0 || year > 2500) throw new InvalidPublicationYearException("Tried to find books with publication year out of range");
        return allBooks.stream().filter(b -> b.getPublicationYear().equals(year)).collect(Collectors.toList());
    }
}
