package org.example.services;

import org.example.models.Book;
import org.example.repos.BooksRepository;

import java.util.List;

public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getBooksList() {
        return booksRepository.getBooksList();
    }

    public List<Book> findAllAuthorsBooksById(Integer id) {
        return booksRepository.getAllAuthorsBooks(id);
    }

    public Book addBook(Book book) {
        return booksRepository.addBook(book);
    }

    public Boolean isBookExistById(Integer id) {
        return booksRepository.isBookExist(id);
    }

    public Book getBookById(Integer id) {
        return booksRepository.getBookById(id);
    }

    public List<Book> getAllAuthorsBooks(Integer id) {
        return booksRepository.getAllAuthorsBooks(id);
    }



}
