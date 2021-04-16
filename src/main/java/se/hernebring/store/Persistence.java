package se.hernebring.store;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import se.hernebring.domain.Author;
import se.hernebring.domain.Book;
import se.hernebring.domain.Publisher;

public class Persistence {

    private static SessionFactory sessionFactory = null;

    protected static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                    .buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
    
    static void save(Author author) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        session.save(author);
        transaction.commit();
        session.close();
    }

    static void save(Book book) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }
    
    public static void save(Publisher publisher) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        session.save(publisher);
        transaction.commit();
        session.close();
    }
    
    static Book getBook(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        transaction.commit();
        session.close();
        return book;
    }
    
    static Author getAuthor(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Author author = (Author) session.get(Author.class, id);
        try {
            List<Book> books = author.getBooks();
            for(Book book: books) {
                System.out.println(book);
            }
        } catch (NullPointerException ex) {
            //print nothing
        }
        transaction.commit();
        session.close();
        return author;
    }
    
    static Publisher getPublisher(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Publisher publisher = (Publisher) session.get(Publisher.class, id);
        transaction.commit();
        session.close();
        return publisher;
    }

    static void delete(int id) {
        if(id == 1) {
            throw new IllegalArgumentException("Id 1 is used for checking empty databases");
        }
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        session.delete(book);
        transaction.commit();
        session.close();
    }

    static void delete() {
        //delete id 1 for testing purposes
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Book book = (Book) session.get(Book.class, 1);
        session.delete(book);
        transaction.commit();
        session.close();
    }

    public static void connect(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Publisher publisher = (Publisher) session.get(Publisher.class, id);
        Author author = (Author) session.get(Author.class, id);
        publisher.add(author);
        author.add(publisher);
        session.save(publisher);
        session.save(author);
        transaction.commit();
        session.close();
    }

    public static boolean isConnected(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Publisher publisher = (Publisher) session.get(Publisher.class, id);
        Author author = (Author) session.get(Author.class, id);
        boolean pubHasAuth = publisher.getAuthors().contains(author);
        boolean authHasPub = author.getPublishers().contains(publisher);
        transaction.commit();
        session.close();
        return pubHasAuth && authHasPub;
    }

}
