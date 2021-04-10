package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.store.AuthorStorage;
import se.hernebring.store.BookStorage;

public class HarnestPrinter implements Printer {

    private final File tempFile;
    private final BookStorage bookStorage;
    private final AuthorStorage authorStorage;

    public HarnestPrinter(File tempFile, BookStorage bookStorage, AuthorStorage authorStorage) {
        this.tempFile = tempFile;
        this.bookStorage = bookStorage;
        this.authorStorage = authorStorage;
        Printer.disableIllegalAccessWarning();
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            bookStorage.getId(1);
            authorStorage.getId(1);
            writer.write("Database: Is Book null? " + Boolean.toString(bookStorage.isNull()) + '\n');
            bookStorage.createLocalBook();
            writer.write("Local storage: Is Book null? " + Boolean.toString(bookStorage.isNull()) + '\n');
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

}
