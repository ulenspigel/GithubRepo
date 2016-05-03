package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.service.UserService;

import java.io.IOException;

public class DeleteUserRequest extends AbstractRequest implements IUserMaintenanceRequest {
    private static Logger logger = LogManager.getLogger(DeleteUserRequest.class.getName());
    private User user;

    public DeleteUserRequest(String submitter, String requestMessage) {
        super(submitter, requestMessage);
    }

    @Override
    public void process() {
        logger.info("Processing user deletion request (nickname = " + user.getNickname() + ")");
        UserService userService = new UserService(this);
        userService.deleteUser();
    }

    @Override
    public void parseRequest() throws IOException {
        logger.debug("Parsing DELETE USER request:\n" + requestMessage);
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(requestMessage, User.class);
        logger.debug("DELETE USER request has been parsed");
    }

    @Override
    public User getUser() {
        return user;
    }
}
