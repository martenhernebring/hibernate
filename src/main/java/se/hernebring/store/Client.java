package se.hernebring.store;

import java.lang.reflect.Field;

import se.hernebring.domain.OldBook;

public class Client {

    private OldBook oldBook;
    private int id;
    //0:id 1:title 2:isbn
    private static final int ISBN_FIELD = 2;
    
    public Client() {
        reset();
    }
    
    public void reset() {
        this.oldBook = new OldBook("Effective Java 3rd Edition", 9780134685991L, "Joshua Bloch");
        this.id = 0; 
    }

    public Client(String[] args) {
        oldBook = new OldBook(args[0]);
        if (args.length > 2) {
            oldBook.setIsbn(Long.parseLong(args[1]));
            oldBook.setAuthor(args[2]);
        }
    }

    @Override
    public String toString() {
        return oldBook.toString();
    }

    public void save(int times) {
        for(int i = 0; i < times; i++) {
            save();
        }
    }
    
    public void save() {
        Server.save(oldBook);  
    }
    
    public boolean isNull() {
        return oldBook == null ? true : false;
    }

    public void getId(int id) {
        this.id = id;
        oldBook = Server.get(id);
    }

    public void updateAuthor(String name) {
        oldBook.setAuthor(name);
    }
    
    public void updatePages(Integer numberOfPages) {
        oldBook.setNumberOfPages(numberOfPages);
    }
    
    public Integer getPages() {
        return oldBook.getNumberOfPages();
    }

    public void delete() {
        Server.delete(id);
        this.id = -1;
        this.oldBook = null;
    }

    public String getRegisteredName(boolean storage) {
        if(storage) {
            return Server.getRegisteredName(getRegisteredName(false));
        } else {
            Field[] allFields = OldBook.class.getDeclaredFields();
            return allFields[ISBN_FIELD].getName();   
        }
    }

}
