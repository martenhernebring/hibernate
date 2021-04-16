package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.hernebring.domain.Book;

public class BookTest {

    static Book title;
    static Book title2;
    static Book title3;

    @BeforeAll
    public static void init() {
        title = new Book("Title");
        title2 = new Book("Title");
        title3 = new Book("Title");
    }

    @Test
    public void createBook() {
        assertNotNull(title);
        assertNotNull(title.getTitle());
    }

    @Test
    public void sameTitle() {
        assertEquals(title.getTitle(), title3.getTitle());
    }
    
    @Test
    public void correctToString() {
        assertEquals("Book [title=" + title.getTitle() + "]", title.toString());
    }
    
    @Test
    public void hasEmptyConstructorForHibernate() {
        Book title4 = new Book();
        assertNull(title4.getTitle());
    }
    
    @Test
    public void changeTitle() {
        title2.setTitle("Other");
        assertNotEquals(title.getTitle(), title2.getTitle());
    }
    
    @Test
    public void isEqual() {
        assertEquals(title, title3);
    }

}
