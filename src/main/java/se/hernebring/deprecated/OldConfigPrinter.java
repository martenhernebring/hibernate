package se.hernebring.deprecated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.print.Printer;

@Deprecated
public class OldConfigPrinter implements Printer {
    private final File tempFile;
    private final OldBookStorage oldBookStorage;
    private final boolean ovning1;

    public OldConfigPrinter(File tempFile, OldBookStorage oldBookStorage) {
        this.tempFile = tempFile;
        this.oldBookStorage = oldBookStorage;
        Printer.disableIllegalAccessWarning();
        oldBookStorage.getId(1);
        if (oldBookStorage.isNull()) {
            ovning1 = true;
            oldBookStorage.reset();
            oldBookStorage.save(3);
            oldBookStorage.getId(3);
        } else {
            ovning1 = false; // 2+
        }
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(oldBookStorage.toString() + '\n');
            oldBookStorage.getId(2);
            oldBookStorage.updateOldAuthor("Joshua J. Bloch");
            writer.write(oldBookStorage.toString() + '\n');
            oldBookStorage.getId(3);
            if (ovning1) {
                oldBookStorage.delete();
            }
            writer.write("Is OldBook null? " + Boolean.toString(oldBookStorage.isNull()) + '\n');
            // Övning 2
            oldBookStorage.getId(1);
            writer.write(oldBookStorage.getOldRegisteredName(false) + '\n');
            writer.write(oldBookStorage.getOldRegisteredName(true) + '\n');
            // Övning 3
            oldBookStorage.getId(2);
            oldBookStorage.updateOldAuthor("Joshua Bloch");
            oldBookStorage.save();
            oldBookStorage.getId(4);
            writer.write(oldBookStorage.toString() + '\n');
            Integer nop = 416;
            oldBookStorage.updateOldPages(nop);
            oldBookStorage.save();
            oldBookStorage.getId(5);
            try {
                writer.write(oldBookStorage.getOldPages() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
