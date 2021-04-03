package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.hibernate.MappingException;
import org.hibernate.service.classloading.spi.ClassLoadingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import se.hernebring.domain.Book;
import se.hernebring.store.BookDecorator;

public class DomainTest {

    static Book title;
    static Book titleIsbnAuthor;

    @BeforeAll
    public static void init() {
        title = new Book("Title");
        titleIsbnAuthor = new Book("Title", "Isbn", "Author");
    }

    @Test
    public void createBook() {
        assertNotNull(title);
    }

    @Test
    public void sameTitle() {
        assertEquals(title.getTitle(), titleIsbnAuthor.getTitle());
    }

    @Test
    public void isbnNotNull() {
        assertNotNull(titleIsbnAuthor.getIsbn());
    }

    @Test
    public void authorEqualsString() {
        assertEquals(titleIsbnAuthor.getAuthor(), "Author");
    }

    @Test
    public void isEqual() {
        assertEquals(title, titleIsbnAuthor);
    }
    
    @Test
    public void addIsbn() {
        title.setIsbn("978-0134685991");
        assertEquals(title.getIsbn(), "978-0134685991");
    }
    
    @Test
    public void addAuthor() {
        title.setAuthor("Joshua Bloch");
        assertEquals(title.getAuthor(), "Joshua Bloch");
    }

}
