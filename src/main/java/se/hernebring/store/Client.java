package se.hernebring.store;

import java.lang.reflect.Field;

import se.hernebring.domain.Book;
import se.hernebring.domain.OldBook;
import se.hernebring.domain.Title;

public class Client {

    private Title title;
    private int id;
    // 0:id 1:title 2:isbn
    private static final int ISBN_FIELD = 2;

    public Client() {
        title = new Book("Effective Java 3rd Edition");
    }

    public void reset() {
        title = new OldBook("Effective Java 3rd Edition", 9780134685991L, "Joshua Bloch");
        this.id = 0;
    }

    public Client(String[] args) {
        if (Character.isDigit(args[0].trim().charAt(0))) {
            reset();
        } else {
            title = new OldBook(args[0]);
            if (args.length > 2) {
                ((OldBook) title).setIsbn(Long.parseLong(args[1]));
                ((OldBook) title).setAuthor(args[2]);
            }
        }
    }

    @Override
    public String toString() {
        return title.toString();
    }

    public void save(int times) {
        for (int i = 0; i < times; i++) {
            save();
        }
    }

    public void save() {
        Server.save(title);
    }

    public boolean isNull() {
        return title == null ? true : false;
    }

    public void getId(int id) {
        this.id = id;
        title = OldServer.get(id, title);
    }
    
    public void delete() {
        OldServer.delete(id);
        this.id = -1;
        title = null;
    }

    public void updateOldAuthor(String name) {
        ((OldBook) title).setAuthor(name);
    }

    public void updateOldPages(Integer numberOfPages) {
        ((OldBook) title).setNumberOfPages(numberOfPages);
    }

    public Integer getOldPages() {
        return ((OldBook) title).getNumberOfPages();
    }

    public String getOldRegisteredName(boolean storage) {
        if (storage) {
            return OldServer.getRegisteredName(getOldRegisteredName(false));
        } else {
            Field[] allFields = OldBook.class.getDeclaredFields();
            return allFields[ISBN_FIELD].getName();
        }
    }

}
