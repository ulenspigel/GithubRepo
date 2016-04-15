package ua.dkovalov.socialnetwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.request.IUserMaintenanceRequest;
import ua.dkovalov.socialnetwork.validator.UserRequestValidator;

public class UserService {
    private static Logger logger = LogManager.getLogger(UserService.class.getName());
    private UserRequestValidator validator;
    private IUserMaintenanceRequest request;

    public UserService(IUserMaintenanceRequest request) {
        this.request = request;
        validator = new UserRequestValidator();
        validator.setRequest(request);
    }

    public void createUser() {
        logger.info("Validating user creation request");
        validator.validateSubmitter();
        validator.checkNicknameUniqueness();
        UserDAO.saveUser(request.getUser());
    }

    public void deleteUser() {
        logger.info("Validating user deletion request");
        validator.validateSubmitter();
        validator.checkAlreadyExists();
        UserDAO.deleteUser(request.getUser());
    }
}
