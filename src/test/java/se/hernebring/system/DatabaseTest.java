package se.hernebring.system;

import static org.junit.jupiter.api.Assertions.fail;

import org.hibernate.MappingException;
import org.hibernate.service.classloading.spi.ClassLoadingException;
import org.junit.jupiter.api.Test;

import se.hernebring.domain.Book;
import se.hernebring.store.BookDecorator;

public class DatabaseTest {
    
    @Test
    public void saveOneBookIfMappingAndDriverIsCorrect() {
        Book title = new Book("Title");
        var decorator = new BookDecorator(title);
        try {
            decorator.save();
        } catch (MappingException | ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
        }
    }
    
    @Test
    public void saveTwoMoreBooksIfMappingAndDriverIsCorrect() {
        Book titleIsbnAuthor = new Book("Title", "Isbn", "Author");
        var decorator = new BookDecorator(titleIsbnAuthor);
        try {
            decorator.save();
        } catch (MappingException | ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
        }
        titleIsbnAuthor = new Book("Effective Java 3rd Edition", "978-0134685991", "Joshua Bloch");
        decorator = new BookDecorator(titleIsbnAuthor);
        try {
            decorator.save();
        } catch (MappingException | ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
        }
    }
    
}
