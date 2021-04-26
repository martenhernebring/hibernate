package se.hernebring.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.LazyInitializationException;
import org.hibernate.MappingException;
import org.hibernate.service.classloading.spi.ClassLoadingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import se.hernebring.app.Main;

public class PublisherToAuthorTest {
    
    private static List<String> lines;

    @BeforeAll
    public static void setUp() {
        final File tempFile = new File("many_to_many_test.tmp");
        String[] noArgs = {};

        try {
            Main.main(noArgs);
        } catch (MappingException ex) {
            fail("Item was not saved. Problem with property access in code and field access in database.");
            ex.printStackTrace();
        } catch (ClassLoadingException ex) {
            fail("Item was not saved. Problem with database-jar/dependency.");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            fail("Requested item does not exist.");
            ex.printStackTrace();
        } catch (LazyInitializationException ex) {
            fail("Session was closed before able to retrieve items related to the one fetched.");
            ex.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            fail();
        }
        tempFile.deleteOnExit();
        for(String line: lines) {
            System.out.println(line);
        }
    }
    
    @Test
    public void verifyNothingStoredInDatabase() {
        assertEquals("Database: Is Publisher empty? true", lines.get(0).trim());
        assertEquals("Database: Is Book and Author empty? true", lines.get(1).trim());
    }
    
    @Test
    public void verifyPublisherAndAuthorStoredInDatabase() {
        assertEquals("Database: Is Publisher empty? false", lines.get(2).trim());
        assertEquals("Database: Is Book and Author not empty? true", lines.get(3).trim());
    }
    
    @Test
    public void verifyPublisherAndAuthorConnected() {
        assertEquals("PublisherController: Is Author and Publisher connected? true", lines.get(4).trim());
    }
    
    @Test
    public void verifyTwoPublishersAndAuthorsStoredInDatabase() {
        assertEquals("Database: Is second Publisher not empty? true", lines.get(5).trim());
        assertEquals("Database: Is second Author not empty? true", lines.get(6).trim());
    }
}
