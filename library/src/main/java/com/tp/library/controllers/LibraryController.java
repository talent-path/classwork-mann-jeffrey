package com.tp.library.controllers;

import com.tp.library.exceptions.*;
import com.tp.library.models.Book;
import com.tp.library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryService service;

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity getBookById(@PathVariable Integer id) {
        Book toReturn = null;
        try {
            toReturn = service.getBookById(id);
        } catch (InvalidBookIdException | NullArgumentException ex) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("/book/title/{title}")
    public ResponseEntity getBookByTitle(@PathVariable String title) {
        List<Book> toReturn = null;
        try {
            toReturn = service.getAllBooksByTitle(title);
        } catch (InvalidTitleException | NullArgumentException ex) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("/book/author/{author}")
    public ResponseEntity getBookByAuthor(@PathVariable String author) {
        List<Book> toReturn = null;
        try {
            toReturn = service.getAllBooksByAuthor(author);
        } catch (InvalidAuthorsException | NullArgumentException ex) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("/book/year/{year}")
    public ResponseEntity getBookByPublicationYear(@PathVariable Integer year) {
        List<Book> toReturn = null;
        try {
            toReturn = service.getAllBooksByPublicationYear(year);
        } catch (InvalidPublicationYearException | NullArgumentException ex) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

    @PostMapping("/book")
    public ResponseEntity createBook(@RequestBody BookRequest newBook) {
        Book toReturn = null;
        try {
            toReturn = service.createBook(newBook.title, newBook.authors, newBook.publicationYear);
        } catch (NullArgumentException | InvalidTitleException
                | InvalidAuthorsException | InvalidPublicationYearException
                | InvalidBookIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

    @PutMapping("/book/edit/{id}")
    public ResponseEntity editBook(@PathVariable Integer id, @RequestBody BookRequest bookEdit) {
        Book edited = null;
        try {
            edited = service.editBook(id, bookEdit.title, bookEdit.authors, bookEdit.publicationYear);
        } catch (InvalidBookIdException | NullArgumentException
                | InvalidTitleException | InvalidPublicationYearException
                | InvalidAuthorsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(edited);
    }
}
