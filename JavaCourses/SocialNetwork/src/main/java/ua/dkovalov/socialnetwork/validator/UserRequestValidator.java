package ua.dkovalov.socialnetwork.validator;

import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.request.AbstractRequest;

public class UserRequestValidator extends AbstractRequestValidator<User> {

    public UserRequestValidator() {
        super(UserRequestValidator.class);
    }

    public UserRequestValidator(AbstractRequest<User> request) {
        this();
        this.request = request;
    }

    public void checkAlreadyExists() {
        String nickname = request.getParsedObject().getNickname();
        if (UserDAO.fetchUser(nickname) == null) {
            throwValidationError("User with nickname \"" + nickname + "\" doesn't exists");
        }
    }

    public void checkNicknameUniqueness() {
        String nickname = request.getParsedObject().getNickname();
        if (UserDAO.fetchUser(nickname) != null) {
            throwValidationError("User with nickname \"" + nickname + "\" already exists");
        }
    }
}
