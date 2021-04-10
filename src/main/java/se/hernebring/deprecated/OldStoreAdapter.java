package se.hernebring.deprecated;

import java.lang.reflect.Field;

import se.hernebring.domain.Title;
import se.hernebring.store.Persistence;
import se.hernebring.store.Store;

@Deprecated
public class OldStoreAdapter implements Store {

    private Title title;
    private int id;
    // 0:id 1:title 2:isbn
    private static final int ISBN_FIELD = 2;
    
    public OldStoreAdapter(String[] args) {
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

    public void reset() {
        title = new OldBook("Effective Java 3rd Edition", 9780134685991L, "Joshua Bloch");
        this.id = 0;
    }

    @Override
    public void save() {
        Persistence.save(title);
    }

    @Override
    public boolean isNull() {
        return title == null ? true : false;
    }

    @Override
    public void getId(int id) {
        this.id = id;
        title = OldPersistence.get(id);
    }
    
    @Override
    public void delete() {
        OldPersistence.delete(id);
        this.id = -1;
        title = null;
    }
    
    public void save(int times) {
        for (int i = 0; i < times; i++) {
            save();
        }
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
            return OldPersistence.getRegisteredName(getOldRegisteredName(false));
        } else {
            Field[] allFields = OldBook.class.getDeclaredFields();
            return allFields[ISBN_FIELD].getName();
        }
    }
    
    @Override
    public String toString() {
        return title.toString();
    }

}
