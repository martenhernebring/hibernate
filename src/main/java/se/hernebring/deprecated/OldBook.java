package se.hernebring.deprecated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Deprecated
@Entity
@Table(name = "OLD_BOOK")
public class OldBook {

    // Property access with Auto generate id to be used as primary key required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    @Column(name = "ISBN_NUM")
    private Long isbn;
    private String author;
    private String publisher;

    @Transient
    private Integer numberOfPages;

    // Hibernate requirement
    public OldBook() {
    }

    public OldBook(String title) {
        this.title = title;
    }

    public OldBook(String title, long isbn, String author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public int getId() {
        return id;
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

    public String getPublisher() {
        return this.publisher;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "OldBook[author=" + author + ",title=" + title + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OldBook oldBook = (OldBook) o;
        return new EqualsBuilder().append(title, oldBook.title).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).toHashCode();
    }

    public int compareTo(OldBook oldBook) {
        return new CompareToBuilder().append(this.title, oldBook.title).toComparison();
    }

}
