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

public class ConfigurationTest {
    
    private static List<String> lines;
    
    @BeforeAll
    public static void setUp() {
        final File tempFile = new File("config_test.tmp");
        String[] noArgs = {};
        try {
            Main.main(noArgs);
        } catch (MappingException | ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
        } catch (NullPointerException ex) {
            fail("Request book does not exist. Was it maybe deleted previously?");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            fail();
        }
        tempFile.deleteOnExit();
    }
    
    @Test
    public void saveSameBook3timesVerifyId1() {
        assertEquals("Book[author=Joshua Bloch,title=Effective Java 3rd Edition]", lines.get(0).trim());
    }
    
    @Test
    public void authorWasUpdatedInDatabase() {
        assertEquals("Book[author=Joshua J. Bloch,title=Effective Java 3rd Edition]", lines.get(1).trim());
    }
    
    @Test
    public void deleteOneBook() {
        assertEquals("NullPointerException thrown", lines.get(2).trim());
    }
    
    @Test
    public void bookSecondFieldIsIsbn() {
        assertEquals("isbn", lines.get(3).trim());
    }
    
    @Test
    public void storedSecondFieldIsIsbnNum() {
        assertEquals("ISBN_NUM", lines.get(4).trim());
    }
}
