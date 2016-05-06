package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.dao.PostDAO;
import ua.dkovalov.socialnetwork.entity.Post;
import ua.dkovalov.socialnetwork.service.PostService;

import java.io.IOException;

public class DeletePostRequest extends AbstractRequest<Post> {
    private static final Logger logger = LogManager.getLogger(DeletePostRequest.class);
    private Post post;

    public DeletePostRequest(String submitter, String requestMessage) {
        super(submitter, requestMessage);
    }

    @Override
    public void parseRequest() throws IOException {
        logger.debug("Parsing DELETE POST request:\n" + requestMessage);
        ObjectMapper mapper = new ObjectMapper();
        post = mapper.readValue(requestMessage, Post.class);
        logger.info("DELETE POST request has been parsed");
    }

    private void calculateFields() {
        post = PostDAO.fetchPost(post.getPostId());
    }

    @Override
    public void process() {
        logger.info("Processing post deletion request (post ID = " + post.getPostId() + ")");
        calculateFields();
        PostService service = new PostService(this);
        service.deletePost();
    }

    @Override
    public Post getParsedObject() {
        return post;
    }
}
