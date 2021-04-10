package se.hernebring.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import se.hernebring.domain.Author;
import se.hernebring.domain.Book;
import se.hernebring.domain.Title;

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

    public static void save(Title title) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        session.save(title);
        transaction.commit();
        session.close();
    }
    
    static void save(Author author) {
        // TODO Auto-generated method stub
        
    }
    
    static Title getBook(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        transaction.commit();
        session.close();
        return book;
    }
    
    static Author getAuthor(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    static void delete(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        session.delete(book);
        transaction.commit();
        session.close();
    }

}
