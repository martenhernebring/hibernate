package se.hernebring.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import se.hernebring.domain.OldBook;
import se.hernebring.domain.Title;

public class Server {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
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

    public static Title get(int id, Title title) {
        var factory = Server.getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        title = (Title) session.get(title.getClass(), id);
        transaction.commit();
        session.close();
        return title;
    }

    public static void delete(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        OldBook oldBook = (OldBook) session.get(OldBook.class, id);
        session.delete(oldBook);
        transaction.commit();
        session.close();
    }

    public static String getRegisteredName(String propertyName) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var aep = ((AbstractEntityPersister) session.getSessionFactory().getClassMetadata(OldBook.class));
        String[] properties = aep.getPropertyNames();
        String registeredName = null;
        for (int nameIndex = 0; nameIndex != properties.length; nameIndex++) {
            if (properties[nameIndex] == propertyName) {
                String[] columns = aep.getPropertyColumnNames(nameIndex);
                registeredName = columns[0];
            }
        }
        session.close();
        return registeredName;
    }
}
