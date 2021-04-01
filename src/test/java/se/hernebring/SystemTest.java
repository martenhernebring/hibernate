package se.hernebring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.ginsberg.junit.exit.FailOnSystemExit;

public class SystemTest {

    @Test
    @FailOnSystemExit
    public void systemExitNotCalled() {
        Main.main(null);
    }
    
    @Test
    public void printBook() {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Main.main(new String[]{"Title"});
        assertEquals("Book[author=null,title=Title]", outputStreamCaptor.toString().trim());
    }
}