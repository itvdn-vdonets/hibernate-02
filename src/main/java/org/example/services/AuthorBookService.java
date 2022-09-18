package org.example.services;

import org.example.models.AuthorBookDto;
import org.example.repos.AuthorBookRepository;
import org.example.repos.AuthorRepository;
import org.example.repos.BooksRepository;

import java.util.List;

public class AuthorBookService {

    private final AuthorBookRepository authorBookRepository;
    private final AuthorRepository authorRepository;
    private final BooksRepository booksRepository;

    public AuthorBookService(AuthorBookRepository authorBookRepository, AuthorRepository authorRepository, BooksRepository booksRepository) {
        this.authorBookRepository = authorBookRepository;
        this.authorRepository = authorRepository;
        this.booksRepository = booksRepository;
    }

    public void addAuthorToBook(Integer authorId, Integer bookId) {
        if (!authorRepository.isAuthorExist(authorId) || !booksRepository.isBookExist(bookId)) {
            throw new RuntimeException("the author or the book doesn't exist!");
        }
        authorBookRepository.addAuthorToBook(authorId, bookId);
    }

    public List<AuthorBookDto> getAuthorBookDtoByAuthorId(Integer id) {
        return authorBookRepository.getAuthorBookDtoByAuthorId(id);
    }

    public List<AuthorBookDto> getAuthorBookDtoByBookId(Integer id) {
        return authorBookRepository.getAuthorBookDtoByBookId(id);
    }
}
