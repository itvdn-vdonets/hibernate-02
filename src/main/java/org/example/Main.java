package com.itvdn.jdbcandhibernate.ex_003_hibernate_get_and_insert;

import com.itvdn.jdbcandhibernate.ex_003_hibernate_get_and_insert.entity.Author;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
        String[] names = {"Pushkin", "Lermontov", "Shevchenko"};

        for (String name : names) {
            Author author = new Author();
            author.setName(name);
            ah.addAuthor(author);
        }

        List<Author> authorList = ah.getAuthorList();

        for (Author author : authorList) {
            System.out.println(author.getId() + " " + author.getName());
        }

        Author author = ah.getAuthorById(5);
        System.out.println(author.getName());
    }

}
