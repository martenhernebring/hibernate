package se.hernebring.deprecated;

import java.lang.reflect.Field;

import se.hernebring.store.Persistence;
import se.hernebring.store.Storage;

@Deprecated
public class OldBookStorage implements Storage {

    private OldBook oldBook;
    private int id;
    // 0:id 1:oldBook 2:isbn
    private static final int ISBN_FIELD = 2;
    
    public OldBookStorage(String[] args) {
        if (Character.isDigit(args[0].trim().charAt(0))) {
            reset();
        } else {
            oldBook = new OldBook(args[0]);
            if (args.length > 2) {
                ((OldBook) oldBook).setIsbn(Long.parseLong(args[1]));
                ((OldBook) oldBook).setAuthor(args[2]);
            }
        }
    }

    public void reset() {
        oldBook = new OldBook("Effective Java 3rd Edition", 9780134685991L, "Joshua Bloch");
        this.id = 0;
    }

    @Override
    public void save() {
        OldPersistence.save(oldBook);
    }

    @Override
    public boolean isNull() {
        return oldBook == null ? true : false;
    }

    public void getId(int id) {
        this.id = id;
        oldBook = OldPersistence.getOldBook(id);
    }
    
    @Override
    public void delete() {
        OldPersistence.delete(id);
        this.id = -1;
        oldBook = null;
    }
    
    public void save(int times) {
        for (int i = 0; i < times; i++) {
            save();
        }
    }

    public void updateOldAuthor(String name) {
        ((OldBook) oldBook).setAuthor(name);
    }

    public void updateOldPages(Integer numberOfPages) {
        ((OldBook) oldBook).setNumberOfPages(numberOfPages);
    }

    public Integer getOldPages() {
        return ((OldBook) oldBook).getNumberOfPages();
    }

    public String getOldRegisteredName(boolean storage) {
        if (storage) {
            return OldPersistence.getRegisteredName(getOldRegisteredName(false));
        } else {
            Field[] allFields = OldBook.class.getDeclaredFields();
            return allFields[ISBN_FIELD].getName();
        }
    }
    
    @Override
    public String toString() {
        return oldBook.toString();
    }

}
