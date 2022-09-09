package org.example.models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicUpdate
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Integer salary;

    @ManyToMany
    @JoinTable(
            name = "Authors_Books",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id")}
    )
    private Set<Book> books;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Photo> photos;

    @OneToOne(mappedBy = "author")
    @PrimaryKeyJoinColumn
    private Address address;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", books=" + books +
                ", photos=" + photos +
                ", address=" + address +
                '}';
    }
}
