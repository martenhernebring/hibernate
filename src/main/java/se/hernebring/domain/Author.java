package se.hernebring.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/*A book can be written by only one author but an author can write one or more books.*/
@Entity
public class Author implements Comparable<Author>{
    
    // Property access with Auto generate id to be used as primary key required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String address;
    @OneToMany
    private List<Book> books;
    @ManyToMany
    private Set<Publisher> publishers;
    @Transient
    private int age;

    public Author(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    // Hibernate requirement
    public Author() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void add(Book book) {
        books.add(book);
    }
    
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }
    
    public void add(Publisher publisher) {
        this.publishers.add(publisher);
    }
    
    public Set<Publisher> getPublishers() {
        return Collections.unmodifiableSet(publishers);
    }
    
    @Override
    public String toString() {
        return "Author [name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Author author = (Author) o;
        return new EqualsBuilder().append(author, author.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    public int compareTo(Author author) {
        return new CompareToBuilder().append(this.id, author.id).toComparison();
    }

}
