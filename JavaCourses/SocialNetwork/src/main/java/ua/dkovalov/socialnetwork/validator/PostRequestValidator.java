package ua.dkovalov.socialnetwork.validator;

import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.entity.Post;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.request.AbstractRequest;
import ua.dkovalov.socialnetwork.util.Constant;

public class PostRequestValidator extends AbstractRequestValidator<Post> {

    public PostRequestValidator() {
        super(PostRequestValidator.class);
    }

    public PostRequestValidator(AbstractRequest<Post> request) {
        this();
        this.request = request;
    }

    public void validateSubmitter() {
        User postAuthor = request.getParsedObject().getAuthor();
        if (postAuthor == null) {
            throwValidationError("Request submitter \"" + request.getSubmitter() + "\" hasn't been recognized as a registered user");
        }

        if (!postAuthor.getUserType().getBrief().equals(Constant.END_USER_BRIEF)) {
            throwValidationError("User \"" + request.getSubmitter() + "\" has invalid type for posts submission");
        }
    }

    public void validatePostOwnership() {
        if (!UserDAO.isUserAdmin(request.getSubmitter())) {
            User postAuthor = request.getParsedObject().getAuthor();
            if (!postAuthor.getNickname().equals(request.getSubmitter())) {
                throwValidationError("Post could be deleted only by its author or admins. User \"" +
                        request.getSubmitter() + "\" doesn't satisfy this condition.");
            }
        }
    }
}
