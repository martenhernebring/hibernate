package se.hernebring.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.MappingException;
import org.hibernate.service.classloading.spi.ClassLoadingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    public void saveSameBook3timesVerifyId3() {
        assertEquals("Book[author=Joshua Bloch,title=Effective Java 3rd Edition]", lines.get(0).trim());
    }
    
    @Test
    public void updateAuthorInDatabase() {
        assertEquals("Book[author=Joshua J. Bloch,title=Effective Java 3rd Edition]", lines.get(1).trim());
    }
    
    @Test
    public void deleteOneBook() {
        assertEquals("NullPointerException thrown", lines.get(2).trim());
    }
}
