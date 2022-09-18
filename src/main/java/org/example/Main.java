package org.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.models.Author;
import org.example.models.AuthorBookDto;
import org.example.models.Book;
import org.example.repos.AuthorBookRepository;
import org.example.repos.AuthorRepository;
import org.example.repos.BooksRepository;
import org.example.services.AuthorBookService;
import org.example.services.AuthorsService;
import org.example.services.BooksService;

import java.util.List;
import java.util.Random;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        BooksRepository booksRepository = new BooksRepository();
        AuthorBookRepository authorBookRepository = new AuthorBookRepository();

        AuthorsService authorsService = new AuthorsService(authorRepository);
        BooksService booksService = new BooksService(booksRepository);
        AuthorBookService authorBookService = new AuthorBookService(authorBookRepository, authorRepository, booksRepository);

        String[] names = {"David Baldacci", "Colleen Hoover", "Stuart Woods", "Nora Roberts", "Michael Connelly"};
        String[] books = {"Book1", "Book2", "Book3", "Book4", "Book5"};

        Random random = new Random();
        int x = 0;
        for (String name : names) {
            Author author = new Author();
            author.setId(++x);
            author.setName(name);
            author.setSalary((double) random.nextInt(5000));
            authorsService.addAuthor(author);
        }


        for (int i = 0; i < books.length; i++) {
            Book tempBook = new Book();
            System.out.println(tempBook);
            tempBook.setName(books[i]);
            booksService.addBook(tempBook);
        }

        List<Author> authorList = authorsService.getAuthorList();
        System.out.println("All authors: " + authorList);

        List<Author> authorListWithSql = authorsService.getAuthorListWithSql();
        System.out.println("All authors with SQL: " + authorListWithSql);

        Author author = authorsService.getAuthorById(2);
        System.out.println("Author[id2]: " + author);

        List<Author> authorsWithSalaryRangeTaxesIncluded = authorsService.findAuthorsWithSalaryRange(true,2000.0, 4000.0);
        List<Author> authorsWithSalaryRangeWithoutTaxes = authorsService.findAuthorsWithSalaryRange(false,2000.0, 4000.0);
        System.out.println("Authors salary range with taxes: " + authorsWithSalaryRangeTaxesIncluded);
        System.out.println("Authors salary range without taxes: " + authorsWithSalaryRangeWithoutTaxes);

        authorBookService.addAuthorToBook(1, 3);
        authorBookService.addAuthorToBook(1, 1);
        authorBookService.addAuthorToBook(2, 2);
        authorBookService.addAuthorToBook(3, 2);

        List<Book> authorsBooksById = booksService.getAllAuthorsBooks(1);
        System.out.println("All books: " + authorsBooksById);

        List<AuthorBookDto> authorBookDtoByAuthorId = authorBookService.getAuthorBookDtoByAuthorId(1);
        List<AuthorBookDto> authorBookDtoByBookId = authorBookService.getAuthorBookDtoByBookId(1);
        System.out.println("Author with id 1 that participated in writing these books:" + authorBookDtoByAuthorId);
        System.out.println("Author that has written books with id 1:" + authorBookDtoByBookId);
    }

}
