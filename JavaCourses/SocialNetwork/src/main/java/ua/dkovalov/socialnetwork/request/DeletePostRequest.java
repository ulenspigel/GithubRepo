package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.dkovalov.socialnetwork.dao.PostDAO;
import ua.dkovalov.socialnetwork.entity.Post;
import ua.dkovalov.socialnetwork.service.PostService;

import java.io.IOException;

public class DeletePostRequest extends AbstractRequest<Post> {
    private static final Logger logger = LogManager.getLogger(DeletePostRequest.class);
    @Autowired
    private PostService service;
    private Post post;

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
        service.setRequest(this).deletePost();
    }

    @Override
    public Post getParsedObject() {
        return post;
    }
}
