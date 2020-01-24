package ru.azat.counter.service;

import ru.azat.counter.model.Counter;

public interface CounterService {
    Counter incrementCounter(Long counterId);

    Counter getCounter(Long counterId);

    void update(Counter counter);
}
