package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import se.hernebring.domain.OldBook;

public class BookTest {

    static OldBook title;
    static OldBook titleIsbnAuthor;

    @BeforeAll
    public static void init() {
        title = new OldBook("Title");
        titleIsbnAuthor = new OldBook("Title", 9780134686097L, "Author");
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
        assertEquals("Author", titleIsbnAuthor.getAuthor());
    }

    @Test
    public void isEqual() {
        assertEquals(title, titleIsbnAuthor);
    }
    
    @Test
    public void addIsbn() {
        title.setIsbn(9780134686097L);
        assertEquals(9780134686097L, title.getIsbn());
    }
    
    @Test
    public void addAuthor() {
        title.setAuthor("Joshua Bloch");
        assertEquals("Joshua Bloch", title.getAuthor());
    }
    
    @Test
    public void addPublisher() {
        title.setPublisher("Addison-Wesley Professional");
        assertEquals("Addison-Wesley Professional", title.getPublisher());
    }
    
    @Test
    public void addNumberOfPages() {
        title.setNumberOfPages(416);
        Integer nop = 416;
        assertEquals(nop, title.getNumberOfPages());
    }

}
