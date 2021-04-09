package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.store.Client;

public class RelationalPrinter implements Printer {

    private final File tempFile;
    private final Client client;

    public RelationalPrinter(File tempFile, Client client) {
        this.tempFile = tempFile;
        this.client = client;
        Printer.disableIllegalAccessWarning();
        client.getId(1);
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            try {
                writer.write(client.toString() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

}
