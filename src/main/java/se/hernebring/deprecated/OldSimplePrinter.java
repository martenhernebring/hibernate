package se.hernebring.deprecated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.print.Printer;

@Deprecated
public class OldSimplePrinter implements Printer{
    private final File tempFile;
    private final OldStoreAdapter oldStoreAdapter;

    public OldSimplePrinter(File tempFile, OldStoreAdapter oldStoreAdapter) {
        this.tempFile = tempFile;
        this.oldStoreAdapter = oldStoreAdapter;
    }

    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(oldStoreAdapter.toString());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
