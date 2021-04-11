package se.hernebring.deprecated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.print.Printer;

@Deprecated
public class OldConfigPrinter implements Printer {
    private final File tempFile;
    private final OldBookController oldBookController;
    private final boolean ovning1;

    public OldConfigPrinter(File tempFile, OldBookController oldBookController) {
        this.tempFile = tempFile;
        this.oldBookController = oldBookController;
        Printer.disableIllegalAccessWarning();
        oldBookController.getId(1);
        if (oldBookController.isNull()) {
            ovning1 = true;
            oldBookController.reset();
            oldBookController.save(3);
            oldBookController.getId(3);
        } else {
            ovning1 = false; // 2+
        }
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(oldBookController.toString() + '\n');
            oldBookController.getId(2);
            oldBookController.updateOldAuthor("Joshua J. Bloch");
            writer.write(oldBookController.toString() + '\n');
            oldBookController.getId(3);
            if (ovning1) {
                oldBookController.delete();
            }
            writer.write("Is OldBook null? " + Boolean.toString(oldBookController.isNull()) + '\n');
            // Övning 2
            oldBookController.getId(1);
            writer.write(oldBookController.getOldRegisteredName(false) + '\n');
            writer.write(oldBookController.getOldRegisteredName(true) + '\n');
            // Övning 3
            oldBookController.getId(2);
            oldBookController.updateOldAuthor("Joshua Bloch");
            oldBookController.save();
            oldBookController.getId(4);
            writer.write(oldBookController.toString() + '\n');
            Integer nop = 416;
            oldBookController.updateOldPages(nop);
            oldBookController.save();
            oldBookController.getId(5);
            try {
                writer.write(oldBookController.getOldPages() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
