package ua.dkovalov.socialnetwork.request;

import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ua.dkovalov.socialnetwork.entity.Post;

public class CreatePostRequest extends AbstractRequest {
    private static Logger logger = LogManager.getLogger(CreatePostRequest.class.getName());
    private Post post;

    public CreatePostRequest(String submitter, String requestMessage) {
        super(submitter, requestMessage);
    }

    @Override
    public void parseRequest() throws IOException {
        
    }

    @Override
    public void process() {

    }
}
