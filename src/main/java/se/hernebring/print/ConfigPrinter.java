package se.hernebring.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import se.hernebring.store.Client;
import sun.misc.Unsafe;

public class ConfigPrinter implements Printer {
    private final File tempFile;
    private final Client client;
    private final boolean ovning1;

    public ConfigPrinter(File tempFile,Client client) {
        this.tempFile = tempFile;
        this.client = client;
        disableIllegalAccessWarning();
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
            client.updateAuthor("Joshua J. Bloch");
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
            writer.write(client.getRegisteredName(false) + '\n');
            writer.write(client.getRegisteredName(true) + '\n');
            // Övning 3
            client.getId(2);
            client.updateAuthor("Joshua Bloch");
            client.save();
            client.getId(4);
            writer.write(client.toString() + '\n');
            Integer nop = 416;
            client.updatePages(nop);
            client.save();
            client.getId(5);
            try {
                writer.write(client.getPages() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void disableIllegalAccessWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);
            @SuppressWarnings("rawtypes")
            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
                | ClassNotFoundException ex) {
            // ignore
        }
    }
}
