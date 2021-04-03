package se.hernebring.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import se.hernebring.store.Client;
import sun.misc.Unsafe;

public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        if (args != null && args.length > 0 && !args[0].equals("")) {
            printToTempFileForTestingIntegration(args);
        } else {
            var client = new Client();
            disableIllegalAccessWarning();
            client.save(3);
            client.getId(3);
            System.out.println(client);
            client.updateAuthor("Joshua J. Bloch");
            System.out.println(client);
        }
    }
    
    private static void printToTempFileForTestingIntegration(String[] args) {
        File tempFile = new File("print_test.tmp");
        Client cl = new Client(args);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){
            writer.write(cl.toString());
        } catch (IOException ex) {
            //test will fail due to no file
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
        } catch (Exception e) {
            // ignore
        }
    }

}