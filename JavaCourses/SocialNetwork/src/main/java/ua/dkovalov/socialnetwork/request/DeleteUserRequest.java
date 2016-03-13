package ua.dkovalov.socialnetwork.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.dkovalov.socialnetwork.entity.User;
import ua.dkovalov.socialnetwork.service.UserService;

import java.io.IOException;

public class DeleteUserRequest extends AbstractRequest {
    //TODO: logging
    private User user;

    public DeleteUserRequest(String submitter, String requestMessage) {
        super(submitter, requestMessage);
    }

    @Override
    public void process() {
        UserService.deleteUser(this);
    }

    @Override
    public void parseRequest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(requestMessage, User.class);
    }

    public User getUser() {
        return user;
    }
}
