package se.hernebring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/*A book can be written by only one author but an author can write one or more books.*/
@Entity
public class Author {
    
    // Property access with Auto generate id to be used as primary key required by Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String address;
    //@ManyToOne
    //@JoinColumn(name="BOOK_FK")
    //private OldBook oldBook;
    @Transient
    private int age;

    public Author(String name) {
        this.name = name;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }
    
    @Override
    public String toString() {
        return "Author[name=" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Author author = (Author) o;
        return new EqualsBuilder().append(author, author.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }

    public int compareTo(Author author) {
        return new CompareToBuilder().append(this.name, author.name).toComparison();
    }

}
