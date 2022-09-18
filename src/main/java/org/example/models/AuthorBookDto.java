package org.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Books_Authors")
public class AuthorBookDto implements Serializable {

    @Id
    @Column(name = "author_id")
    private Integer authorId;
    @Id
    @Column(name = "book_id")
    private Integer bookId;

    public AuthorBookDto(Integer authorId, Integer bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public AuthorBookDto() {

    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "AuthorBookDto{" +
                "authorId=" + authorId +
                ", bookId=" + bookId +
                '}';
    }
}
