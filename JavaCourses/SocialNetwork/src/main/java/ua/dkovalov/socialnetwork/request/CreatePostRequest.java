package ua.dkovalov.socialnetwork.request;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.entity.Post;
import ua.dkovalov.socialnetwork.service.PostService;

public class CreatePostRequest extends AbstractRequest<Post> {
    private static final Logger logger = LogManager.getLogger(CreatePostRequest.class);
    @Autowired
    private PostService service;
    private Post post;

    @Override
    public void parseRequest() throws IOException {
        logger.debug("Parsing CREATE POST request:\n" + requestMessage);
        ObjectMapper mapper = new ObjectMapper();
        post = mapper.readValue(requestMessage, Post.class);
        logger.info("CREATE POST request has been parsed");
    }

    private void calculateFields() {
        post.setAuthor(UserDAO.fetchUser(submitter));
        post.setSubmissionDate(new Timestamp(submissionTime.getMillis()));
    }

    @Override
    public void process() {
        logger.info("Processing post creation request (title = " + post.getPostTitle() + ", author = " + submitter + ")");
        calculateFields();
        service.setRequest(this).createPost();
    }

    @Override
    public Post getParsedObject() {
        return post;
    }
}
