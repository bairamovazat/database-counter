package ru.azat.counter.service;

import ru.azat.counter.model.Counter;
import ru.azat.counter.repository.CounterRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Базовая реализация класса
 */
public abstract class AbstractCounterService implements CounterService {

    protected CounterRepository counterRepository;

    public AbstractCounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public abstract Counter incrementCounter(Long counterId);

    @Override
    public Counter getCounter(Long counterId) {
        return counterRepository.findOneById(counterId);
    }

    @Override
    public void update(Counter counter) {
        counterRepository.update(counter);
    }
}
