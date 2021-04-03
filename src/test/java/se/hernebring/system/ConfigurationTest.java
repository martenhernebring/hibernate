package se.hernebring.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.hibernate.MappingException;
import org.hibernate.service.classloading.spi.ClassLoadingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.hernebring.app.Main;

public class ConfigurationTest {
    
    private static String[] lines;
    
    @BeforeEach
    public void setUp() {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String[] noArgs = {};
        try {
            Main.main(noArgs);
        } catch (MappingException | ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
        }
        lines = outputStreamCaptor.toString().split("\\r?\\n");
    }
    
    @Test
    public void saveSameBook3timesVerifyId3() {
        assertEquals("Book[author=Joshua Bloch,title=Effective Java 3rd Edition]", lines[lines.length - 1]);
    }
    
    /*@Test
    public void updateAuthorInDatabase() {
        String[] noArgs = {};
        try {
            Main.main(noArgs);
        } catch (MappingException | ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
        }
        String output = outputStreamCaptor.toString();
        assertEquals("Book[author=Joshua Bloch,title=Effective Java 3rd Edition]", output.substring(output.lastIndexOf('\n') + 1));
    }*/
    
    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }
}
