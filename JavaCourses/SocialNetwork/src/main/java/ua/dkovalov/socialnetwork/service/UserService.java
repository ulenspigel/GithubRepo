package ua.dkovalov.socialnetwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.request.AbstractRequest;
import ua.dkovalov.socialnetwork.validator.UserRequestValidator;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private UserRequestValidator validator;
    private AbstractRequest<User> request;

    public UserService(AbstractRequest<User> request) {
        this.request = request;
        validator = new UserRequestValidator(request);
    }

    public UserService() {
    }

    public UserRequestValidator getValidator() {
        return validator;
    }

    public void setValidator(UserRequestValidator validator) {
        this.validator = validator;
    }

    public AbstractRequest<User> getRequest() {
        return request;
    }

    public UserService setRequest(AbstractRequest<User> request) {
        this.request = request;
        return this;
    }

    public void createUser() {
        validator.validateSubmitterIsAdmin();
        validator.checkNicknameUniqueness();
        logger.info("Request for user creation has been validated");
        UserDAO.saveUser(request.getParsedObject());
        logger.info("User data has been persisted in database");
    }

    public void deleteUser() {
        validator.validateSubmitterIsAdmin();
        validator.checkAlreadyExists();
        logger.info("Request for user deletion has been validated");
        UserDAO.deleteUser(request.getParsedObject());
        logger.info("User data has been deleted from database");
    }
}
