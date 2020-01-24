package ru.azat.counter.service;

import org.hibernate.LockMode;
import org.hibernate.Session;
import ru.azat.counter.model.Counter;
import ru.azat.counter.repository.CounterRepository;

/**
 * Реализация счётчика с помощью пессимистичной блокировки на стороне БД
 */
public class SynchronizedCounterServiceImpl extends AbstractCounterService {

    public SynchronizedCounterServiceImpl(CounterRepository counterRepository) {
        super(counterRepository);
    }

    @Override
    public Counter incrementCounter(Long counterId) {
        return counterRepository.incrementValueById(counterId, 1L, LockMode.PESSIMISTIC_WRITE);
    }

}
