package se.hernebring.store;

import org.hibernate.Session;

import se.hernebring.domain.Book;

public class BookDecorator {

    private Book book = null;

    public BookDecorator(String[] args) {
        if (args == null || args[0].equals("")) {
            throw new IllegalArgumentException("Empty title is not allowed");
        }
        book = new Book(args[0]);
        if (args.length > 2) {
            book.setIsbn(args[1]);
            book.setAuthor(args[2]);
        }
    }

    public BookDecorator(Book book) {
        this.book = book;
    }

    public void save() {
        var factory = Setup.getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    @Override
    public String toString() {
        return " " + book.toString();
    }

    

}
