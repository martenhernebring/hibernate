package se.hernebring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Book {
    // Property access with Auto generate id to be used as primary key required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    public Book(String title) {
        this.title = title;
    }

    //Hibernate requirement
    public Book() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Book[title=" + title + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return new EqualsBuilder().append(id, book.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    public int compareTo(Book book) {
        return new CompareToBuilder().append(this.id, book.id).toComparison();
    }

}
