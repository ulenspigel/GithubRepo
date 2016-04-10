package ua.dkovalov.socialnetwork.service;

import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.request.CreateUserRequest;
import ua.dkovalov.socialnetwork.request.DeleteUserRequest;
import ua.dkovalov.socialnetwork.validator.UserRequestValidator;

public class UserService {
    private UserRequestValidator validator = new UserRequestValidator();

    public void createUser(CreateUserRequest request) {
        validator.setRequest(request);
        validator.validateSubmitter();
        validator.checkNicknameUniqueness();
        UserDAO.saveUser(request.getUser());
    }

    public void deleteUser(DeleteUserRequest request) {
        validator.setRequest(request);
        validator.validateSubmitter();
        validator.checkAlreadyExists();
        UserDAO.deleteUser(request.getUser());
    }
}
