package ua.dkovalov.socialnetwork.util;

import ua.dkovalov.socialnetwork.request.CreateUserRequest;

import java.io.IOException;

public class RequestMapper {
    public static CreateUserRequest mapRequest(String requestMessage) throws IOException {
        // TODO: parse out from JSON the request type
        CreateUserRequest createUserRequest = new CreateUserRequest(requestMessage);
        createUserRequest.parseRequest();
        return createUserRequest;
    }
}
