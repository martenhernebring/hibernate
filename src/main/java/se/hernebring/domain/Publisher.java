package se.hernebring.domain;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Publisher implements Comparable<Publisher>{
    
    // Property access with Auto generate id to be used as primary key required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String email;
    private String address;
    
    @ManyToMany(mappedBy = "publishers")
    private Set<Author> authors;

    public Publisher(String name) {
        this.name = name;
    }

    //Hibernate requirement
    public Publisher() {}
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;   
    }
    
    public void add(Author author) {
        this.authors.add(author);
    }
    
    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    @Override
    public String toString() {
        return "Publisher [name=" + name + ", email=" + email + "]";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Publisher publisher = (Publisher) o;
        return new EqualsBuilder().append(publisher, publisher.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    public int compareTo(Publisher publisher) {
        return new CompareToBuilder().append(this.id, publisher.id).toComparison();
    }
    

}
