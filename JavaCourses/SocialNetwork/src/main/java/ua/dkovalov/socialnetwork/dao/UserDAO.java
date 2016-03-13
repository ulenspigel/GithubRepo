package ua.dkovalov.socialnetwork.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ua.dkovalov.socialnetwork.dao.util.DAOUtil;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.entity.UserType;

public class UserDAO {
    private static Logger logger = LogManager.getLogger(UserDAO.class.getName());

    public static void saveUser(User user) {
        Session session = DAOUtil.openSession();
        logger.info("Inserting an entry to T_USER:\n" + user);
        session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
            logger.info("Insertion complete");
        } catch (HibernateException he) {
            logger.error("Error during insertion:", he);
            throw he;
        } finally {
            session.close();
        }
    }

    public static void deleteUser(User user) {
        Session session = DAOUtil.openSession();
        logger.info("Deleting an entry from T_USER with nickname = " + user.getNickname());
        session.beginTransaction();
        try {
            session.createQuery("delete from User where upper(nickname) = :nickname").
                    setParameter("nickname", user.getNickname().toUpperCase()).executeUpdate();
            session.getTransaction().commit();
            logger.info("Deletion complete");
        } catch (HibernateException he) {
            logger.error("Error during deletion:", he);
            throw he;
        } finally {
            session.close();
        }
    }
}
