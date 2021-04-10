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
            writer.write("Database: Is Book empty? " + Boolean.toString(bookStorage.isNull()) + '\n');
            authorStorage.getId(1);
            writer.write("Database: Is Author empty? " + Boolean.toString(authorStorage.isNull()) + '\n');
            bookStorage.createLocal();
            writer.write("Local storage: Is Book null? " + Boolean.toString(bookStorage.isNull()) + '\n');
            authorStorage.createLocal();
            writer.write("Local storage: Is Author null? " + Boolean.toString(authorStorage.isNull()) + '\n');
            //Book has Many to One relation to Author
            writer.write("Book: Is Author null? " + bookStorage.authorIsNull() + '\n');
            bookStorage.allocate(authorStorage.getAuthor());
            writer.write("Book: Is Author null? " + bookStorage.authorIsNull() + '\n');
            authorStorage.save();
            bookStorage.save();
            //Check if local data was saved with Id1
            bookStorage.getId(1);
            authorStorage.getId(1);
            writer.write("Database: Is Book empty? " + Boolean.toString(bookStorage.isNull()) + '\n');
            writer.write("Database: Is Author empty? " + Boolean.toString(authorStorage.isNull()) + '\n');
            writer.write("Printing out book stored in database: " + bookStorage + '\n');
            writer.write("Name of the author for this book: " + bookStorage + '\n');
            bookStorage.createLocal("Java Puzzlers With Access Codes");
            bookStorage.allocate(authorStorage.getAuthor());
            writer.write("Book: Is Author null? " + bookStorage.authorIsNull() + '\n');
            bookStorage.save();
            bookStorage.getId(2);
            writer.write("Database: Was the second book saved? " + Boolean.toString(!bookStorage.isNull()) + '\n');
            bookStorage.delete();
            bookStorage.getId(2);
            writer.write("Database: Was the second book deleted? " + Boolean.toString(bookStorage.isNull()) + '\n');
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

}
