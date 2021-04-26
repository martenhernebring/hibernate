package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.store.AuthorController;
import se.hernebring.store.PublisherController;

public class PublisherToAuthorPrinter implements Printer {

    private File tempFile;
    private AuthorController authorController;
    private PublisherController publisherController;

    public PublisherToAuthorPrinter(File tempFile, AuthorController authorController, PublisherController publisherController) {
        this.tempFile = tempFile;
        this.authorController = authorController;
        this.publisherController = publisherController;
        Printer.disableIllegalAccessWarning();
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            publisherController.getFirstIdFromStorage();
            writer.write("Database: Is Publisher empty? " + Boolean.toString(publisherController.isNull()) + '\n');
            authorController.getFirstIdsFromStorage();
            writer.write("Database: Is Book and Author empty? " + Boolean.toString(authorController.isNull()) + '\n');
            publisherController.save("Addison-Wesley Professional");
            publisherController.getFirstIdFromStorage();
            writer.write("Database: Is Publisher empty? " + Boolean.toString(publisherController.isNull()) + '\n');
            authorController.saveAnAuthorWith3Books();
            authorController.getFirstIdsFromStorage();
            writer.write("Database: Is Book and Author not empty? " + Boolean.toString(authorController.isNotNull()) + '\n');
            publisherController.connect(authorController);
            writer.write("PublisherController: Is Author and Publisher connected? " + Boolean.toString(publisherController.isConnected(authorController)) + '\n');
            publisherController.save("Pearson");
            authorController.savePearsonAuthorWith2Books();
            publisherController.connect(authorController);
            writer.write("Database: Is second Publisher not empty? " + publisherController.getId(2));
            writer.write("Database: Is second Author not empty? " + authorController.getId(2));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
