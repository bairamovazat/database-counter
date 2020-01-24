package ru.azat.counter.service;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.azat.counter.model.Counter;


import java.util.Properties;

public class DaoServiceImpl implements DaoService {

    private Configuration configuration;

    private SessionFactory sessionFactory;

    public DaoServiceImpl(Properties properties) {
        configuration = new Configuration()
                .addAnnotatedClass(Counter.class)
                .setProperties(properties);
        this.sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
