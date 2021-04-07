package se.hernebring.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import se.hernebring.store.Client;
import sun.misc.Unsafe;

public class Main {

    final static File tempFile = new File("config_test.tmp");

    protected Main() {
    }

    public static void main(String[] args) {
        if (args != null && args.length > 0 && !args[0].equals("")) {
            printToTempFileForTestingIntegration(args);
        } else {
            disableIllegalAccessWarning();
            ovningar1Task4();
        }
    }

    private static void ovningar1Task4() {
        var client = new Client();
        //client.save(3); //Övning 1
        //client.getId(3); //Övning 1
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            client.getId(1);
            writer.write(client.toString() + '\n');
            client.getId(2);
            client.updateAuthor("Joshua J. Bloch");
            writer.write(client.toString() + '\n');
            client.getId(3);
            //client.delete(); //Övning 1
            try {
                writer.write(client.toString() + '\n');
            } catch (NullPointerException ex) {
                writer.write("NullPointerException thrown\n");
            }
            //Övning 2
            client.getId(1);
            writer.write(client.getRegisteredName(false) + '\n');
            writer.write(client.getRegisteredName(true) + '\n');
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void printToTempFileForTestingIntegration(String[] args) {
        File tempFile = new File("print_test.tmp");
        var client = new Client(args);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(client.toString());
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