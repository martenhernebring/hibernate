package se.hernebring.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class Setup {
    
    private static SessionFactory sessionFactory = null;
    
    private static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            var config = new Configuration();
            config.configure();
            var serviceReg = new ServiceRegistryBuilder()
                    .applySettings(config.getProperties())
                    .buildServiceRegistry();
            sessionFactory = config.buildSessionFactory(serviceReg);
        }
        return sessionFactory;
    }
}
