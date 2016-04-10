package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.entity.UserType;
import ua.dkovalov.socialnetwork.service.UserService;
import java.io.IOException;

public class CreateUserRequest extends AbstractRequest implements IUserMaintenanceRequest {
    private static Logger logger = LogManager.getLogger(CreateUserRequest.class.getName());
    private static final UserType END_USER_TYPE = UserDAO.fetchEndUserType();
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

    private void setCalculatedFields() {
        //TODO: add fields "is active" and "creation date"
        user.setUserType(END_USER_TYPE);
    }

    @Override
    public void process() {
        logger.info("Processing user creation request");
        setCalculatedFields();
        UserService userService = new UserService();
        userService.createUser(this);
    }

    @Override
    public User getUser() {
        return user;
    }
}
