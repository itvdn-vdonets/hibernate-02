package org.example.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static SessionFactory factory;

    static {
        try {
             factory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void closeSessionFactory() {
        factory.close();
    }

}
