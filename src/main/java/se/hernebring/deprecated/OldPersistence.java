package se.hernebring.deprecated;

import org.hibernate.Session;
import org.hibernate.persister.entity.AbstractEntityPersister;

import se.hernebring.store.Persistence;

@Deprecated
class OldPersistence extends Persistence {

    static OldBook getOldBook(int id) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        OldBook ob = (OldBook) session.get(OldBook.class, id);
        transaction.commit();
        session.close();
        return ob;
    }
    
    public static void save(OldBook oldBook) {
        var factory = getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        session.save(oldBook);
        transaction.commit();
        session.close();
    }

    static void delete(int id) {
        var factory = Persistence.getSessionFactory();
        Session session = factory.openSession();
        var transaction = session.beginTransaction();
        OldBook oldBook = (OldBook) session.get(OldBook.class, id);
        session.delete(oldBook);
        transaction.commit();
        session.close();
    }

    static String getRegisteredName(String propertyName) {
        var factory = Persistence.getSessionFactory();
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
