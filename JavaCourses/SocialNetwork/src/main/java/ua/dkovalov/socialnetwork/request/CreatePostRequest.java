package ua.dkovalov.socialnetwork.request;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.joda.time.DateTime;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.entity.Post;
import ua.dkovalov.socialnetwork.service.PostService;

public class CreatePostRequest extends AbstractRequest<Post> {
    private static final Logger logger = LogManager.getLogger(CreatePostRequest.class);
    private DateTime submissionTime;
    private Post post;

    public CreatePostRequest(String submitter, String requestMessage, DateTime submissionTime) {
        super(submitter, requestMessage);
        this.submissionTime = submissionTime;
    }

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
        PostService service = new PostService(this);
        service.createPost();
    }

    @Override
    public Post getParsedObject() {
        return post;
    }
}
