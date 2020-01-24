package ru.azat.counter.repository;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.azat.counter.model.Counter;
import ru.azat.counter.service.DaoService;

import java.util.function.Consumer;
import java.util.function.Function;

public class CounterRepositoryImpl implements CounterRepository {

    private DaoService daoService;
    private SessionFactory sessionFactory;

    public CounterRepositoryImpl(DaoService daoService) {
        this.daoService = daoService;
        sessionFactory = this.daoService.getSessionFactory();
    }

    @Override
    public Counter findOneById(Long counterId) {
        Session session = sessionFactory.openSession();
        Counter counter = session.get(Counter.class, counterId);
        session.close();
        return counter;
    }

    @Override
    public Counter incrementValueById(Long counterId, Long value, LockMode lockMode) {
        return executeInTransactionAndCommit((session -> {
            Counter counter = session.get(Counter.class, counterId, LockMode.PESSIMISTIC_WRITE);
            counter.setValue(counter.getValue() + 1);
            return counter;
        }));
    }

    @Override
    public void save(Counter counter) {
        executeInTransactionAndCommit((session -> {
            session.save(counter);
        }));
    }

    @Override
    public void saveOrUpdate(Counter counter) {
        executeInTransactionAndCommit((session -> {
            session.saveOrUpdate(counter);
        }));
    }

    @Override
    public void update(Counter counter) {
        executeInTransactionAndCommit((session -> {
            session.update(counter);
        }));
    }

    @Override
    public void delete(Counter counter) {
        executeInTransactionAndCommit((session -> {
            session.delete(counter);
        }));
    }

    private <T> T executeInTransactionAndCommit(Function<Session, T> function) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T result = null;
        try {
            result = function.apply(session);
            session.getTransaction().commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            session.close();
        }

        return result;
    }

    private void executeInTransactionAndCommit(Consumer<Session> function) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            function.accept(session);
            session.getTransaction().commit();
        } catch (Throwable e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            session.close();
        }

    }
}
