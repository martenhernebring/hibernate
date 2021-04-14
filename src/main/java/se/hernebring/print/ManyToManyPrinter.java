package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.store.AuthorController;
import se.hernebring.store.PublisherController;

public class ManyToManyPrinter implements Printer {

    private File tempFile;
    private AuthorController authorController;
    private PublisherController publisherController;

    public ManyToManyPrinter(File tempFile, AuthorController authorController, PublisherController publisherController) {
        this.tempFile = tempFile;
        this.authorController = authorController;
        this.publisherController = publisherController;
        Printer.disableIllegalAccessWarning();
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            authorController.getFirstIdsFromStorage();
            writer.write("Database: Is Publisher empty? " + Boolean.toString(publisherController.isNull()) + '\n');
            /*authorController.saveAnAuthorWith3Books();
            authorController.getFirstIdsFromStorage();
            writer.write("Database: Is Book and Author not empty? " + Boolean.toString(authorController.isNotNull()) + '\n');
            writer.write("Author: Number of books added? " + Integer.toString(authorController.getNumberOfBooks()) + '\n');*/
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
