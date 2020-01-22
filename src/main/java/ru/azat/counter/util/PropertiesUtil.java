package ru.azat.counter.util;

import net.bytebuddy.implementation.bytecode.Throw;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties loadPropertyFromResource(String path) {
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            return null;
        }

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log(e);
            return null;
        }
        return properties;
    }

    private static void log(Throwable throwable) {
        //Нету логера
    }
}
