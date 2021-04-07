package se.hernebring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Table; To change table name

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
//@Table(name = "TBL_BOOK") To change table name
public class Book {

    // Auto generate id to be used as primary key required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    @Column (name="ISBN_NUM")
    private Long isbn;
    private String author;

    private String publisher;

    // Hibernate requirement
    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, long isbn, String author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }
    
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book[author=" + author + ",title=" + title + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;

        return new EqualsBuilder().append(title, book.title).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).toHashCode();
    }

    public int compareTo(Book book) {
        return new CompareToBuilder().append(this.title, book.title).toComparison();
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return this.publisher;
    }

}
