package se.hernebring.store;

import se.hernebring.domain.Book;

public class BookController implements Controller {

    private Book book;
    private int id;

    public BookController() {
        create("Effective Java 3rd Edition");
    }
    
    public void create(String title) {
        book = new Book(title);
    }

    @Override
    public void save() {
        Persistence.save(book);
    }
    
    @Override
    public boolean isNull() {
        return book == null ? true : false;
    }
    
    @Override
    public void delete() {
        Persistence.delete(id);
        this.id = -1;
        book = null;
    }
    
    @Override
    public String toString() {
        return book.toString();
    }
    
    public Book getId(int id) {
        this.id = id;
        book = (Book) Persistence.getBook(id);
        return book;
    }

}
