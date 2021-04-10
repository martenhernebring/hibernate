package se.hernebring.deprecated;

import org.hibernate.Session;
import org.hibernate.persister.entity.AbstractEntityPersister;

import se.hernebring.domain.Title;
import se.hernebring.store.Server;

@Deprecated
public class OldServer {

    public static Title get(int id, Title title) {
        var factory = Server.getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        title = (OldBook) session.get(OldBook.class, id);
        transaction.commit();
        session.close();
        return title;
    }

    public static void delete(int id) {
        var factory = Server.getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        OldBook oldBook = (OldBook) session.get(OldBook.class, id);
        session.delete(oldBook);
        transaction.commit();
        session.close();
    }

    public static String getRegisteredName(String propertyName) {
        var factory = Server.getSessionFactory();
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
