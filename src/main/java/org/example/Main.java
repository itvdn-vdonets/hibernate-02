package org.example;

import org.example.models.Author;
import org.example.repos.AuthorRepository;

import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        logger.warn("TEST");
        AuthorRepository authorRepository = new AuthorRepository();
        String[] names = {"David Baldacci", "Colleen Hoover", "Stuart Woods", "Nora Roberts", "Michael Connelly"};

        Random random = new Random();
        for (String name : names) {
            Author author = new Author();
            author.setName(name);
            author.setSalary(random.nextInt(5000));
            authorRepository.addAuthor(author);
        }

        List<Author> authorList = authorRepository.getAuthorList();
        System.out.println("All authors: "+authorList);

        List<Author> authorListWithSql = authorRepository.getAuthorListWithSql();
        System.out.println("All authors with SQL: "+authorListWithSql);

        Author author = authorRepository.getAuthorById(2);
        System.out.println("Author[id2]: "+author);

        List<Author> authorsWithSalaryRange = authorRepository.findAuthorsWithSalaryRange(3000, 4000);
        System.out.println("Authors salary range: "+authorsWithSalaryRange);
        //Hibernate: update Author set name=?, salary=? where id=?
        //Hibernate: update Author set name=? where id=?
        authorRepository.updateAuthorsName(2L, "Ihor Kosolapow");
        System.out.println("Author[id2] with changed name: "+authorRepository.getAuthorById(2));

    }

}
