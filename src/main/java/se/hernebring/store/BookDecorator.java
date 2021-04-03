package se.hernebring.store;

import org.hibernate.Session;

import se.hernebring.domain.Book;

public class BookDecorator {

    private Book book = null;

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
    
    public Book get(int id) {
        var factory = Setup.getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        book = (Book)session.get(Book.class, 3);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public String toString() {
        return " " + book.toString();
    }

}
