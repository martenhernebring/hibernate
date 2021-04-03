package se.hernebring.store;

import se.hernebring.domain.Book;

public class Client {

    private Book book;
    private int id;
    
    public Client() {
        this.book = new Book("Effective Java 3rd Edition", "978-0134685991", "Joshua Bloch");
        noIdSelected();
    }

    private void noIdSelected() {
        this.id = -1;
    }

    public Client(String[] args) {
        book = new Book(args[0]);
        if (args.length > 2) {
            book.setIsbn(args[1]);
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

}
