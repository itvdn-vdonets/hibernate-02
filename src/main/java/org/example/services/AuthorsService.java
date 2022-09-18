package org.example.services;

import org.example.models.Author;
import org.example.repos.AuthorRepository;

import java.util.List;

public class AuthorsService {
    private final AuthorRepository authorRepository;

    public AuthorsService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author updateAuthorsName(Integer id, String newNew) {
        Boolean bookExist = authorRepository.isAuthorExist(id);
        Author author;
        if (bookExist) {
            author = authorRepository.updateAuthorsName(id, newNew);
        } else {
            throw new RuntimeException("The user with id " + id + " doesn't exist");
        }
        return author;
    }

    public List<Author> getAuthorList() {
        return authorRepository.getAuthorList();
    }

    /*HQL*/
    @SuppressWarnings("unchecked")
    public List<Author> findAuthorsWithSalaryRange(Boolean isTaxIncluded, Double minSalary, Double maxSalary) {
        if (isTaxIncluded) {
            minSalary -= minSalary * 0.15;
            maxSalary -= maxSalary * 0.15;
        }
        return authorRepository.findAuthorsWithSalaryRange(minSalary, maxSalary);
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAuthorListWithSql() {
        return authorRepository.getAuthorListWithSql();
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.getAuthorById(id);
    }

    public Author addAuthor(Author author) {
        System.out.println(author);
        if (author == null) {
            throw new ClassCastException("Author cannot be null!");
        }
        if (isAuthorExist(author.getId())) {
            throw new RuntimeException("Author with id " + author.getId() + " already exist!");
        }
        Author author1 = authorRepository.addAuthor(author);
        return author1;
    }

    @SuppressWarnings("uncheked")
    Boolean isAuthorExist(Integer id) {
        return authorRepository.isAuthorExist(id);
    }
}
