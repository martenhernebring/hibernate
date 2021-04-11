package se.hernebring.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.MappingException;
import org.hibernate.service.classloading.spi.ClassLoadingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import se.hernebring.app.Main;

public class HarnestTest {

    private static List<String> lines;

    @BeforeAll
    public static void setUp() {
        final File tempFile = new File("relational_test.tmp");
        String[] noArgs = {};

        try {
            Main.main(noArgs);
        } catch (MappingException ex) {
            fail("Book was not saved. Problem with property access in code and field access in database.");
            ex.printStackTrace();
        } catch (ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            fail("Requested book does not exist.");
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
        assertEquals("Database: Is Book and Author empty? true", lines.get(0).trim());
    }
    
    @Test
    public void verifyBookAndAuthorStoredInDatabase() {
        assertEquals("Database: Is Book and Author not empty? true", lines.get(1).trim());
    }
    
    @Test
    public void verifyAuthorHas3Books() {
        assertEquals("Author: Number of books added? 3", lines.get(2).trim());
    }
}
