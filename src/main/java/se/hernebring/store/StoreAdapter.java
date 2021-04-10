package se.hernebring.store;

import se.hernebring.domain.Book;
import se.hernebring.domain.Title;

public class StoreAdapter implements Store {

    private Title title;
    private int id;

    public StoreAdapter() {
        title = new Book("Effective Java 3rd Edition");
    }

    public void save() {
        Persistence.save(title);
    }

    public boolean isNull() {
        return title == null ? true : false;
    }

    public void getId(int id) {
        this.id = id;
        title = Persistence.get(id);
    }
    
    public void delete() {
        Persistence.delete(id);
        this.id = -1;
        title = null;
    }
    
    @Override
    public String toString() {
        return title.toString();
    }

}
