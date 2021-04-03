package se.hernebring.app;

import java.lang.reflect.Field;

import se.hernebring.domain.Book;
import se.hernebring.store.BookDecorator;
import sun.misc.Unsafe;

public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        if (args != null && args.length > 0 && !args[0].equals("")) {
            printBookFromArgsContainingTitle(args);
        } else {
            Book book = new Book("Effective Java 3rd Edition", "978-0134685991", "Joshua Bloch");
            var decorator = new BookDecorator(book);
            disableIllegalAccessWarning();
            decorator.save();
            decorator.save();
            decorator.save();
            System.out.print(decorator.get(3));
        }
    }

    private static void printBookFromArgsContainingTitle(String[] args) {
        Book book = new Book(args[0]);
        if (args.length > 2) {
            book.setIsbn(args[1]);
            book.setAuthor(args[2]);
        }
        System.out.println(book);
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