package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import se.hernebring.app.Main;

public class PrintTest {

    private static File tempFile = new File("print_test.tmp");

    @Test
    public void title() {
        Main.main(new String[] { "Title" });
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            line = reader.readLine();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            fail();
        }
        assertEquals("Book[author=null,title=Title]", line.trim());
    }

    @Test
    public void titleAuthor() {
        Main.main(new String[] { "Title", "Isbn", "Author" });
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            line = reader.readLine();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            fail();
        }
        assertEquals("Book[author=Author,title=Title]", line.trim());
    }

    @AfterAll
    public static void deleteTempFile() {
        tempFile.deleteOnExit();
    }
}