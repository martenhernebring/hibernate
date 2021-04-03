package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import se.hernebring.domain.Book;
import se.hernebring.store.Server;

public class DatabaseTest {
    @Test
    void getBookWithId3(){
        Book book = Server.get(3);
        assertNull(book);
    }
}
