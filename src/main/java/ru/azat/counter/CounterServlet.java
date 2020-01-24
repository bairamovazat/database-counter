package ru.azat.counter;


import ru.azat.counter.model.Counter;
import ru.azat.counter.repository.CounterRepository;
import ru.azat.counter.repository.CounterRepositoryImpl;
import ru.azat.counter.service.*;
import ru.azat.counter.util.PropertiesUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class CounterServlet extends HttpServlet {

    private CounterService counterService;
    private CounterRepository counterRepository;

    public CounterServlet() {
        initContext();
    }

    public void initContext() {
        Properties databaseProperties = PropertiesUtil.loadPropertyFromResource("hibernate.properties");
        if(databaseProperties == null) {
            throw new IllegalArgumentException("hibernate.properties не найдены");
        }

        DaoService daoService = new DaoServiceImpl(databaseProperties);
        counterRepository = new CounterRepositoryImpl(daoService);
        counterService = new SynchronizedCounterServiceImpl(counterRepository);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Counter counter = counterService.incrementCounter(1L);
        request.setAttribute("counter", counter);
        request.getRequestDispatcher("counter.jsp").forward(request, response);
    }
}
