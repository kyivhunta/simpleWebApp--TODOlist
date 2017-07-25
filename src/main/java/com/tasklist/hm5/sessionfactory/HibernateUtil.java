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
            File file = new File("D:\\GoJava#6\\JavaEE\\TODO List\\src\\rescources\\hibernate.cfg.xml");
            Configuration configuration = new Configuration();
            configuration.configure(file);

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
