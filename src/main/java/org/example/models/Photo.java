package org.example.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "file_name")
    private String fileName;
    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    Author author;

    public Photo(String fileName) {
        this.fileName = fileName;
    }

    public Photo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", author=" + author +
                '}';
    }
}
