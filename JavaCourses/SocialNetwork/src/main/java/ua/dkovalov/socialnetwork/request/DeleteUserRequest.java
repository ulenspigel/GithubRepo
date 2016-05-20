package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.service.UserService;

import java.io.IOException;

public class DeleteUserRequest extends AbstractRequest<User> {
    private static final Logger logger = LogManager.getLogger(DeleteUserRequest.class);
    @Autowired
    private UserService service;
    private User user;

    @Override
    public void process() {
        logger.info("Processing user deletion request (nickname = " + user.getNickname() + ")");
        service.setRequest(this).deleteUser();
    }

    @Override
    public void parseRequest() throws IOException {
        logger.debug("Parsing DELETE USER request:\n" + requestMessage);
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(requestMessage, User.class);
        logger.debug("DELETE USER request has been parsed");
    }

    @Override
    public User getParsedObject() {
        return user;
    }
}
