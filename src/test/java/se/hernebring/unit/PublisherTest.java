package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.hernebring.domain.Publisher;

public class PublisherTest {
    static Publisher publisher;
    
    @BeforeAll
    public static void init() {
        publisher = new Publisher("pubName");
    }
    
    @Test
    public void createPublisher() {
        assertNotNull(publisher);
    }
    
    @Test
    public void nameIsCorrect() {
        String name = publisher.getName();
        assertEquals("pubName", name);
    }
    
    @Test
    public void addEmail() {
        publisher.setEmail("email@example.se");
        assertEquals("email@example.se", publisher.getEmail());
    }
    
    @Test
    public void addAddress() {
        publisher.setAddress("Rödgatan 3, 432-10 Västra Frölunda");
        assertEquals("Rödgatan 3, 432-10 Västra Frölunda", publisher.getAddress());
    }
    
    @Test
    public void correctToString() {
        assertEquals("Publisher [name=" + publisher.getName() + ", email=" + publisher.getEmail() + "]", publisher.toString());
    }
    
    @Test
    public void hasEmptyConstructorForHibernate() {
        publisher = new Publisher();
        assertNull(publisher.getName());
    }
    
    @Test
    public void isEqual() {
        publisher = new Publisher("Name");
        assertEquals(publisher, publisher);
    }
    
    @Test
    public void notEqual() {
        Publisher pub2 = new Publisher("Name");
        assertNotEquals(publisher, pub2);
    }

}
