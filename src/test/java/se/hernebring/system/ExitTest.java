package se.hernebring.system;

import org.junit.jupiter.api.Test;

import com.ginsberg.junit.exit.ExpectSystemExit;

import se.hernebring.app.Main;

public class ExitTest {
    @Test
    @ExpectSystemExit
    public void exitOnNullArg() {
        Main.main(null);
    }
    
    @Test
    @ExpectSystemExit
    public void exitOnNoArgsNotAllowed() {
        Main.main(new String[] { "" });
    }

    @Test
    @ExpectSystemExit
    public void exitOnEmptyTitle() {
        Main.main(new String[] { "", "Isbn", "Author" });
    }
}
