package se.hernebring.store;

import java.lang.reflect.Field;

import se.hernebring.domain.Book;

public class Client {

    private Book book;
    private int id;
    //0:id 1:title 2:isbn
    private static final int ISBN_FIELD = 2;
    
    public Client() {
        this.book = new Book("Effective Java 3rd Edition", 9780134685991L, "Joshua Bloch");
        noIdSelected();
    }

    private void noIdSelected() {
        this.id = -1;
    }

    public Client(String[] args) {
        book = new Book(args[0]);
        if (args.length > 2) {
            book.setIsbn(Long.parseLong(args[1]));
            book.setAuthor(args[2]);
        }
    }

    @Override
    public String toString() {
        return book.toString();
    }

    public void save(int times) {
        for(int i = 0; i < times; i++) {
            Server.save(book);
        }
    }

    public void getId(int id) {
        this.id = id;
        book = Server.get(id);
    }

    public void updateAuthor(String name) {
        book.setAuthor(name);
    }

    public void delete() {
        Server.delete(id);
        noIdSelected();
        this.book = null;
    }

    public String getRegisteredName(boolean storage) {
        if(storage) {
            return Server.getRegisteredName(getRegisteredName(false));
        } else {
            Field[] allFields = Book.class.getDeclaredFields();
            return allFields[ISBN_FIELD].getName();   
        }
    }

}
