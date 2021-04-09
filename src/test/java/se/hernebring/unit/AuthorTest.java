package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import se.hernebring.domain.Author;

public class AuthorTest {
    static Author author;
    
    @BeforeAll
    public static void init() {
        author = new Author("Name");
    }
    
    @Test
    public void createBook() {
        assertNotNull(author);
    }
    
    @Test
    public void nameIsCorrect() {
        String name = author.getName();
        assertEquals("Name", name);
    }
    
    @Test
    public void addressIsCorrect() {
        author.setAddress("Address");
        String address = author.getAddress();
        assertEquals("Address", address);
    }
    
    @Test
    public void ageIsNotNull() {
        assertNotNull(author.getAge());
    }
    
    @Test
    public void correctToString() {
        assertEquals("Author[name=" + author.getName() + "]", author.toString());
    }
    
    @Test
    public void hasEmptyConstructorForHibernate() {
        author = new Author();
        assertNull(author.getName());
    }
}
