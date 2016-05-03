package ua.dkovalov.socialnetwork.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.entity.Post;

import java.io.IOException;

public class DeletePostRequest extends AbstractRequest {
    private static Logger logger = LogManager.getLogger(DeletePostRequest.class.getName());
    private Post post;

    public DeletePostRequest(String submitter, String requestMessage) {
        super(submitter, requestMessage);
    }

    @Override
    public void parseRequest() throws IOException {

    }

    @Override
    public void process() {

    }
}
