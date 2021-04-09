package se.hernebring.print;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public interface Printer {
    public void print();
    static void disableIllegalAccessWarning() {
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
