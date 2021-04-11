package se.hernebring.deprecated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.print.Printer;

@Deprecated
public class OldSimplePrinter implements Printer{
    private final File tempFile;
    private final OldBookController oldBookController;

    public OldSimplePrinter(File tempFile, OldBookController oldBookController) {
        this.tempFile = tempFile;
        this.oldBookController = oldBookController;
    }

    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(oldBookController.toString());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
