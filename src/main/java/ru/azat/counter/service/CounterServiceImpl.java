package ru.azat.counter.service;

import ru.azat.counter.model.Counter;
import ru.azat.counter.repository.CounterRepository;

public class CounterServiceImpl implements CounterService {

    private CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public void incrementCounter(Long counterId) {

    }

    @Override
    public Counter getCounter(Long counterId) {
        return null;
    }
}
