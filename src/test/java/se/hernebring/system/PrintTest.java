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

public class PrintTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printTitle() {
        Main.main(new String[] { "Title" });
        assertEquals("Book[author=null,title=Title]", outputStreamCaptor.toString().trim());
    }

    @Test
    public void printTitleAuthor() {
        Main.main(new String[] { "Title", "Isbn", "Author" });
        assertEquals("Book[author=Author,title=Title]", outputStreamCaptor.toString().trim());
    }

    @Test
    public void saveSameBook3timesAndPrintId3() {
        String[] noArgs = {};
        try {
            Main.main(noArgs);
        } catch (MappingException | ClassLoadingException ex) {
            fail("Book was not saved. Problem with database-jar/dependency.");
        }
        String output = outputStreamCaptor.toString();
        assertEquals("Book[author=Joshua Bloch,title=Effective Java 3rd Edition]", output.substring(output.lastIndexOf('\n') + 1));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}