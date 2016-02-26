package ua.dkovalov.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.dkovalov.socialnetwork.entity.User;

// TODO: session pooling
public class UserDAO {
    private final static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void saveUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
