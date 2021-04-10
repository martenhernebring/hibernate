package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import se.hernebring.store.StoreAdapter;

public class RelationalPrinter implements Printer {

    private final File tempFile;
    private final StoreAdapter storeAdapter;

    public RelationalPrinter(File tempFile, StoreAdapter storeAdapter) {
        this.tempFile = tempFile;
        this.storeAdapter = storeAdapter;
        Printer.disableIllegalAccessWarning();
        storeAdapter.getId(1);
    }

    @Override
    public void print() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Start: Is Book null? " + Boolean.toString(storeAdapter.isNull()) + '\n');
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

}
