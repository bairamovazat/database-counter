package ru.azat.counter.service;

import ru.azat.counter.model.Counter;

public interface CounterService {
    void incrementCounter(Long counterId);

    Counter getCounter(Long counterId);
}
