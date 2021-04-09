package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.store.Client;

public class SimplePrinter implements Printer{
    private final File tempFile;
    private final Client client;

    public SimplePrinter(File tempFile, Client client) {
        this.tempFile = tempFile;
        this.client = client;
    }

    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(client.toString());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
