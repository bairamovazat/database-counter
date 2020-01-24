package ru.azat.counter.service;

import ru.azat.counter.model.Counter;
import ru.azat.counter.repository.CounterRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализация счётчика с помощью Lock на стороне JVM
 */
public class CounterServiceImpl extends AbstractCounterService {

    private Lock incrementLock = new ReentrantLock();

    public CounterServiceImpl(CounterRepository counterRepository) {
        super(counterRepository);
    }

    @Override
    public Counter incrementCounter(Long counterId) {
        Counter counter = null;
        try {
            incrementLock.lock();
            counter = counterRepository.findOneById(counterId);
            counter.setValue(counter.getValue() + 1L);
            counterRepository.update(counter);
        } finally {
            incrementLock.unlock();
        }
        return counter;
    }
}
