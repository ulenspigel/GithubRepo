package ua.dkovalov.socialnetwork.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.dkovalov.socialnetwork.entity.User;

//TODO: session pooling
public class UserDAO {
    private final static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static Logger logger = LogManager.getLogger(UserDAO.class.getName());

    public static void saveUser(User user) {
        Session session = sessionFactory.openSession();
        logger.debug("Inserting an entry to T_USER:\n" + user);
        session.beginTransaction();
        logger.debug("Transaction started");
        try {
            session.save(user);
            logger.debug("Entry inserted");
            session.getTransaction().commit();
            logger.debug("Transaction committed");
        } finally {
            session.close();
            logger.debug("Session closed");
        }
    }
}
