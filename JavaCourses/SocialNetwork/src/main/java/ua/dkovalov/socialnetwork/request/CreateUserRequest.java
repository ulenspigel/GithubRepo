package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.entity.UserType;
import ua.dkovalov.socialnetwork.service.UserService;

import java.io.IOException;

public class CreateUserRequest extends AbstractRequest {
    private static Logger logger = LogManager.getLogger(CreateUserRequest.class.getName());
    private User user;

    public CreateUserRequest(String submitter, String requestMessage) {
        super(submitter, requestMessage);
    }

    @Override
    public void parseRequest() throws IOException {
        logger.debug("Parsing CREATE USER request:\n" + requestMessage);
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(requestMessage, User.class);
        logger.info("CREATE USER request has been parsed");
    }

    @Override
    public void process() {
        //TODO: add fields "is active" and "creation date" and move setting of all these fields into a separate method
        user.setUserType(UserType.USER);
        UserService.createUser(this);
    }

    public User getUser() {
        return user;
    }
}
