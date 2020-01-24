package ru.azat.counter.repository;

import org.hibernate.LockMode;
import org.hibernate.Session;
import ru.azat.counter.model.Counter;

public interface CounterRepository {
    Counter findOneById(Long counterId);

    Counter incrementValueById(Long counterId, Long value, LockMode lockMode);

    void save(Counter counter);

    void saveOrUpdate(Counter counter);

    void update(Counter counter);

    void delete(Counter counter);

}
