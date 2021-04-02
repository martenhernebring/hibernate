package se.hernebring;

import org.junit.jupiter.api.Test;

import com.ginsberg.junit.exit.ExpectSystemExit;

public class ExitTest {
    @Test
    @ExpectSystemExit
    public void nullArgNotAllowed() {
        Main.main(null);
    }
    
    @Test
    @ExpectSystemExit
    public void emptyTitleNotAllowed() {
        Main.main(new String[]{"", "Isbn", "Author"});
    }
}
