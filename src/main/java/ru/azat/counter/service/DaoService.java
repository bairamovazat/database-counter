package ru.azat.counter.service;

import org.hibernate.SessionFactory;

public interface DaoService {
    SessionFactory getSessionFactory();
}
