package ru.azat.counter.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.azat.counter.model.Counter;
import ru.azat.counter.service.DaoService;

public class CounterRepositoryImpl implements CounterRepository {

    private DaoService daoService;
    private SessionFactory sessionFactory;

    public CounterRepositoryImpl(DaoService daoService) {
        this.daoService = daoService;
        sessionFactory = this.daoService.getSessionFactory();
    }

    public Counter getCounter(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Counter.class, id);
    }

}
