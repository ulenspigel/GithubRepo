package ua.dkovalov.socialnetwork.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.entity.UserType;

import java.util.List;

public class UserDAO {
    private static Logger logger = LogManager.getLogger(UserDAO.class.getName());
    private static final String END_USER_BRIEF = "USER";
    private static final String ADMIN_BRIEF = "ADMIN";

    public static void saveUser(User user) {
        logger.info("Inserting an entry to T_USER:\n" + user);
        Session session = DAOUtil.openSession();
        session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
            logger.debug("Insertion complete");
        } catch (HibernateException he) {
            logger.error("Error during insertion:", he);
            throw he;
        } finally {
            session.close();
        }
    }

    public static void deleteUser(User user) {
        logger.info("Deleting an entry from T_USER with nickname = " + user.getNickname());
        Session session = DAOUtil.openSession();
        session.beginTransaction();
        try {
            //noinspection JpaQlInspection
            session.createQuery("delete from User where upper(nickname) = :nickname").
                    setParameter("nickname", user.getNickname().toUpperCase()).executeUpdate();
            session.getTransaction().commit();
            logger.debug("Deletion complete");
        } catch (HibernateException he) {
            logger.error("Error during deletion:", he);
            throw he;
        } finally {
            session.close();
        }
    }

    public static UserType fetchEndUserType() {
        logger.debug("Fetching an entry from REF_USER_TYPE table that corresponds to end user type");
        UserType endUserType = null;
        List userTypes = DAOUtil.openSession().createCriteria(UserType.class).
                                 add(Restrictions.eq("brief", END_USER_BRIEF)).setMaxResults(1).list();
        if (userTypes.size() > 0) {
            endUserType = (UserType) userTypes.get(0);
        } else {
            logger.warn("Unable to fetch a record from REF_USER_TYPE that corresponds to end user type");
        }
        return endUserType;
    }

    public static boolean isUserAdmin(String submitter) {
        User user = fetchUserByNickname(submitter);
        if (user == null) {
            return false;
        }
        return user.getUserType().getBrief().equals(ADMIN_BRIEF);
    }

    public static User fetchUserByNickname(String nickname){
        // TODO: isActive flag
        User user = null;
        List users = DAOUtil.openSession().createCriteria(User.class).add(Restrictions.eq("nickname", nickname.toUpperCase())).
                setReadOnly(true).list();
        if (users.size() > 1) {
            RuntimeException exception = new RuntimeException("Uniqueness was violated for a nickname " + nickname);
            logger.error(exception.getMessage());
            throw exception;
        } else if (users.size() == 1) {
            user = (User) users.get(0);
        }
        return user;
    }
}
