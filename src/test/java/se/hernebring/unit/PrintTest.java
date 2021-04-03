package se.hernebring.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.hernebring.app.Main;

public class PrintTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void title() {
        Main.main(new String[] { "Title" });
        assertEquals("Book[author=null,title=Title]", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ttleAuthor() {
        Main.main(new String[] { "Title", "Isbn", "Author" });
        assertEquals("Book[author=Author,title=Title]", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }
}