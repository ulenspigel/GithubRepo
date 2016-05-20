package ua.dkovalov.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAOUtil {
    private final static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void releaseSession(Session session) {
        session.close();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
