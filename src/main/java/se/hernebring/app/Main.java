package se.hernebring.app;

import java.lang.reflect.Field;

import se.hernebring.store.Client;
import sun.misc.Unsafe;

public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        if (args != null && args.length > 0 && !args[0].equals("")) {
            System.out.println(new Client(args));
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