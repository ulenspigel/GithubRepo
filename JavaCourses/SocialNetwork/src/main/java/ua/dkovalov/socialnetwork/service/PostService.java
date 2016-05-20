package ua.dkovalov.socialnetwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ua.dkovalov.socialnetwork.dao.PostDAO;
import ua.dkovalov.socialnetwork.entity.Post;
import ua.dkovalov.socialnetwork.request.AbstractRequest;
import ua.dkovalov.socialnetwork.validator.PostRequestValidator;

@Service
public class PostService {
    private static final Logger logger = LogManager.getLogger(PostService.class);
    private PostRequestValidator validator;
    private AbstractRequest<Post> request;

    public PostService(AbstractRequest<Post> request) {
        this.request = request;
        validator = new PostRequestValidator(request);
    }

    public PostService() {
    }

    public PostService setRequest(AbstractRequest<Post> request) {
        this.request = request;
        return this;
    }

    public AbstractRequest<Post> getRequest() {
        return request;
    }

    public PostRequestValidator getValidator() {
        return validator;
    }

    public void setValidator(PostRequestValidator validator) {
        this.validator = validator;
    }

    public void createPost() {
        validator.validateSubmitter();
        validator.validatePostDate();
        logger.info("Request for post creation has been validated");
        PostDAO.savePost(request.getParsedObject());
        logger.info("Post data has been persisted in database");
    }

    public void deletePost() {
        validator.validatePostOwnership();
        logger.info("Request for post deletion has been validated");
        PostDAO.deletePost(request.getParsedObject());
        logger.info("Post data has been deleted from database");
    }
}
