package se.hernebring.deprecated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.print.Printer;
import se.hernebring.store.Client;

@Deprecated
public class OldConfigPrinter implements Printer {
    private final File tempFile;
    private final Client client;
    private final boolean ovning1;

    public OldConfigPrinter(File tempFile, Client client) {
        this.tempFile = tempFile;
        this.client = client;
        Printer.disableIllegalAccessWarning();
        client.getId(1);
        if (client.isNull()) {
            ovning1 = true;
            client.reset();
            client.save(3);
            client.getId(3);
        } else {
            ovning1 = false; // 2+
        }
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(client.toString() + '\n');
            client.getId(2);
            client.updateOldAuthor("Joshua J. Bloch");
            writer.write(client.toString() + '\n');
            client.getId(3);
            if (ovning1) {
                client.delete();
            }
            try {
                writer.write(client.toString() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
            // Övning 2
            client.getId(1);
            writer.write(client.getOldRegisteredName(false) + '\n');
            writer.write(client.getOldRegisteredName(true) + '\n');
            // Övning 3
            client.getId(2);
            client.updateOldAuthor("Joshua Bloch");
            client.save();
            client.getId(4);
            writer.write(client.toString() + '\n');
            Integer nop = 416;
            client.updateOldPages(nop);
            client.save();
            client.getId(5);
            try {
                writer.write(client.getOldPages() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
