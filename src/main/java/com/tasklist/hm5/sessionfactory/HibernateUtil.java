package com.tasklist.hm5.sessionfactory;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.io.File;

public class HibernateUtil {


    private static final SessionFactory ourSessionFactory;

    private HibernateUtil() {
    }

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() throws HibernateException {

        return ourSessionFactory;
    }

    public static void shutDown() {

        ourSessionFactory.close();
    }

}
