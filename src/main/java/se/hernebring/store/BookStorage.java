package se.hernebring.store;

import se.hernebring.domain.Book;
import se.hernebring.domain.Title;

public class BookStorage implements Storage {

    private Title title;
    private int id;
    
    public void createLocalBook() {
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
        title = Persistence.getBook(id);
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