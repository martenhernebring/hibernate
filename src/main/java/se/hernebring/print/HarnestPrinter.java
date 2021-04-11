package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import se.hernebring.store.AuthorController;

public class HarnestPrinter implements Printer {

    private final File tempFile;
    private final AuthorController authorController;

    public HarnestPrinter(File tempFile, AuthorController authorController) {
        this.tempFile = tempFile;
        this.authorController = authorController;
        Printer.disableIllegalAccessWarning();
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            authorController.getFirstIdsFromStorage();
            writer.write("Database: Is Book and Author empty? " + Boolean.toString(authorController.isNull()) + '\n');
            authorController.saveAnAuthorWith3Books();
            authorController.getFirstIdsFromStorage();
            writer.write("Database: Is Book and Author not empty? " + Boolean.toString(authorController.isNotNull()) + '\n');
            writer.write("Author: Number of books added? " + Integer.toString(authorController.getNumberOfBooks()) + '\n');
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

}
