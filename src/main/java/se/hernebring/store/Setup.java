package se.hernebring.store;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Setup {
    
    private static SessionFactory sessionFactory = null;
    
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .buildServiceRegistry();
            sessionFactory = 
                     configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
