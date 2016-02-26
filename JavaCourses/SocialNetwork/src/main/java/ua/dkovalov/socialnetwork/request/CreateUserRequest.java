package ua.dkovalov.socialnetwork.request;

/*
User entity: last name; first name; nickname; birth date; gender; is active; creation date; user type;
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.dkovalov.socialnetwork.entity.User;

import java.io.IOException;

public class CreateUserRequest {
    private String requestMessage;
    private User user;

    public CreateUserRequest(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public void parseRequest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(requestMessage, User.class);
    }

    public User getUser() {
        return user;
    }
}
