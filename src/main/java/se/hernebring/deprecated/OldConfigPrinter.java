package se.hernebring.deprecated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.print.Printer;

@Deprecated
public class OldConfigPrinter implements Printer {
    private final File tempFile;
    private final OldStoreAdapter oldStoreAdapter;
    private final boolean ovning1;

    public OldConfigPrinter(File tempFile, OldStoreAdapter oldStoreAdapter) {
        this.tempFile = tempFile;
        this.oldStoreAdapter = oldStoreAdapter;
        Printer.disableIllegalAccessWarning();
        oldStoreAdapter.getId(1);
        if (oldStoreAdapter.isNull()) {
            ovning1 = true;
            oldStoreAdapter.reset();
            oldStoreAdapter.save(3);
            oldStoreAdapter.getId(3);
        } else {
            ovning1 = false; // 2+
        }
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(oldStoreAdapter.toString() + '\n');
            oldStoreAdapter.getId(2);
            oldStoreAdapter.updateOldAuthor("Joshua J. Bloch");
            writer.write(oldStoreAdapter.toString() + '\n');
            oldStoreAdapter.getId(3);
            if (ovning1) {
                oldStoreAdapter.delete();
            }
            try {
                writer.write(oldStoreAdapter.toString() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
            // Övning 2
            oldStoreAdapter.getId(1);
            writer.write(oldStoreAdapter.getOldRegisteredName(false) + '\n');
            writer.write(oldStoreAdapter.getOldRegisteredName(true) + '\n');
            // Övning 3
            oldStoreAdapter.getId(2);
            oldStoreAdapter.updateOldAuthor("Joshua Bloch");
            oldStoreAdapter.save();
            oldStoreAdapter.getId(4);
            writer.write(oldStoreAdapter.toString() + '\n');
            Integer nop = 416;
            oldStoreAdapter.updateOldPages(nop);
            oldStoreAdapter.save();
            oldStoreAdapter.getId(5);
            try {
                writer.write(oldStoreAdapter.getOldPages() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
