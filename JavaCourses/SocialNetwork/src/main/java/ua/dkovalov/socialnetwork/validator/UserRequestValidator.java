package ua.dkovalov.socialnetwork.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.request.IUserMaintenanceRequest;

public class UserRequestValidator {
    private static Logger logger = LogManager.getLogger(UserRequestValidator.class.getName());
    IUserMaintenanceRequest request;

    public UserRequestValidator() {}

    public UserRequestValidator(IUserMaintenanceRequest request) {
        this.request = request;
    }

    public IUserMaintenanceRequest getRequest() {
        return request;
    }

    public void setRequest(IUserMaintenanceRequest request) {
        this.request = request;
    }

    private void throwValidationError(String errorMessage) {
        logger.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }

    public void validateSubmitter() {
        if (!UserDAO.isUserAdmin(request.getSubmitter())) {
            throwValidationError("Request submitter " + request.getSubmitter() + " do not have admin privileges");
        }
    }

    public void checkAlreadyExists() {
        if (UserDAO.fetchUserByNickname(request.getUser().getNickname()) == null) {
            throwValidationError("User with nickname \"" + request.getUser().getNickname() + "\" doesn't exists");
        }
    }

    public void checkNicknameUniqueness() {
        if (UserDAO.fetchUserByNickname(request.getUser().getNickname()) != null) {
            throwValidationError("User with nickname \"" + request.getUser().getNickname() + "\" already exists");
        }
    }
}
