package ua.dkovalov.socialnetwork.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.entity.UserType;
import ua.dkovalov.socialnetwork.util.Constant;
import ua.dkovalov.socialnetwork.util.DAOUtil;

public class UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    public static void saveUser(User user) {
        logger.debug("Inserting an entry into T_USER:\n" + user);
        try (Session session = DAOUtil.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            logger.debug("Insertion complete");
        } catch (HibernateException he) {
            logger.error("Error during insertion of user:\n" + user, he);
            throw he;
        }
    }

    public static void deleteUser(User user) {
        logger.debug("Deleting an entry from T_USER with nickname = " + user.getNickname());
        try (Session session = DAOUtil.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User where upper(nickname) = :nickname").
                    setParameter("nickname", user.getNickname().toUpperCase()).executeUpdate();
            session.getTransaction().commit();
            logger.debug("Deletion complete");
        } catch (HibernateException he) {
            logger.error("Error during deletion of user:\n" + user, he);
            throw he;
        }
    }

    public static UserType fetchEndUserType() {
        logger.debug("Fetching an entry from REF_USER_TYPE table that corresponds to end user type");
        UserType endUserType = null;
        try (Session session = DAOUtil.openSession()) {
            endUserType = (UserType) session.createCriteria(UserType.class).add(Restrictions.eq("brief", Constant.END_USER_BRIEF)).uniqueResult();
        }

        if (endUserType == null) {
            logger.warn("Unable to fetch a record from REF_USER_TYPE that corresponds to end user type");
        }

        return endUserType;
    }

    public static boolean isUserAdmin(String submitter) {
        User user = fetchUser(submitter);
        if (user == null) {
            RuntimeException exception = new RuntimeException("Request submitter " + submitter + " was not recognized as user.");
            logger.error(exception.getMessage());
            throw exception;
        }
        return user.getUserType().getBrief().equals(Constant.ADMIN_BRIEF);
    }

    public static User fetchUser(String nickname) {
        logger.debug("Fetching user with nickname " + nickname);
        User user = null;
        try (Session session = DAOUtil.openSession()) {
            user = (User) session.createCriteria(User.class).add(Restrictions.eq("nickname", nickname.toUpperCase())).uniqueResult();
        } catch (HibernateException he) {
            RuntimeException exception = new RuntimeException("Uniqueness was violated for a nickname " + nickname);
            logger.error(exception.getMessage());
            throw exception;
        }

        return user;
    }

}
