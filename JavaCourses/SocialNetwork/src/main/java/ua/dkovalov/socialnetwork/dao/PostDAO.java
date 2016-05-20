package ua.dkovalov.socialnetwork.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ua.dkovalov.socialnetwork.entity.Post;

public class PostDAO {
    private static final Logger logger = LogManager.getLogger(PostDAO.class);

    public static void savePost(Post post) {
        logger.debug("Inserting an entry into T_POST:\n" + post);
        try (Session session = DAOUtil.openSession()) {
            session.beginTransaction();
            session.save(post);
            session.getTransaction().commit();
            logger.debug("Insertion complete");
        } catch (HibernateException he) {
            logger.error("Error during insertion of post:\n" + post, he);
            throw he;
        }
    }

    public static void deletePost(Post post) {
        logger.debug("Deleting an entry from T_POST with POST_ID=" + post.getPostId());
        try (Session session = DAOUtil.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Post where postId = :postId").setParameter("postId", post.getPostId()).executeUpdate();
            session.getTransaction().commit();
            logger.debug("Deletion complete");
        } catch (HibernateException he) {
            logger.error("Error during deletion of post:\n" + post, he);
            throw he;
        }
    }

    public static final Post fetchPost(Integer postId) {
        Post post = null;
        try (Session session = DAOUtil.openSession()) {
            post = session.get(Post.class, postId);
        }
        return post;
    }
}
