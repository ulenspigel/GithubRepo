package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.entity.UserType;
import ua.dkovalov.socialnetwork.service.UserService;
import java.io.IOException;

public class CreateUserRequest extends AbstractRequest<User> {
    private static final Logger logger = LogManager.getLogger(CreateUserRequest.class);
    private static final UserType END_USER_TYPE = UserDAO.fetchEndUserType();
    @Autowired
    private UserService service;
    private User user;

    @Override
    public void parseRequest() throws IOException {
        logger.debug("Parsing CREATE USER request:\n" + requestMessage);
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(requestMessage, User.class);
        logger.info("CREATE USER request has been parsed");
    }

    private void calculateFields() {
        user.setUserType(END_USER_TYPE);
    }

    @Override
    public void process() {
        logger.info("Processing user creation request (nickname = " + user.getNickname() + ")");
        calculateFields();
        service.setRequest(this).createUser();
    }

    @Override
    public User getParsedObject() {
        return user;
    }
}
