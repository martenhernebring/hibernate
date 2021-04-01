package se.hernebring.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Book {

    private String title;
    private String isbn;
    private String author;

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String isbn, String author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book[author=" + author + ",title=" + title + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;

        return new EqualsBuilder()
            .append(title, book.title)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(title)
            .toHashCode();
    }
    
    public int compareTo(Book book) {
        return new CompareToBuilder()
            .append(this.title, book.title)
            .toComparison();
    }

}
