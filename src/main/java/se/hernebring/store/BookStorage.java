package se.hernebring.store;

import se.hernebring.domain.Author;
import se.hernebring.domain.Book;

public class BookStorage implements Storage {

    private Book book;
    private int id;
    
    public BookStorage() {
        createLocal();
    }
    
    public void createLocal() {
        book = new Book("Effective Java 3rd Edition");
    }
    
    public void createLocal(String title) {
        book = new Book(title);
    }
    
    public void allocate(Author author) {
        book.setAuthor(author);
    }
    
    public String getAuthorName() {
        return book.getAuthor().toString();
    }

    public void save() {
        Persistence.save(book);
    }

    public boolean isNull() {
        return book == null ? true : false;
    }
    
    public boolean authorIsNull() {
        return book.getAuthor() == null ? true : false;
    }

    public void getId(int id) {
        this.id = id;
        book = (Book) Persistence.getBook(id);
    }
    
    public void delete() {
        Persistence.delete(id);
        this.id = -1;
        book = null;
    }
    
    @Override
    public String toString() {
        return book.toString();
    }

}
