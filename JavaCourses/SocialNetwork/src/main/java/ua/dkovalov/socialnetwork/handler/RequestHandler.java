package ua.dkovalov.socialnetwork.handler;

import ua.dkovalov.socialnetwork.request.CreateUserRequest;
import ua.dkovalov.socialnetwork.service.UserService;
import ua.dkovalov.socialnetwork.util.RequestMapper;

import java.io.IOException;

//TODO logging everywhere
public class RequestHandler {
    public void acceptRequest(String requestMessage) throws IOException {
        RequestMapper requestMapper = new RequestMapper();
        CreateUserRequest createUserRequest = requestMapper.mapRequest(requestMessage);
        UserService.createUser(createUserRequest);
    }

    public static void main(String[] args) throws IOException {
        String json =
                "{" +
                        "\"firstName\":\"first\"," +
                        "\"lastName\":\"last\"," +
                        "\"nickname\":\"nick\"" +
                        //"\"gender\":\"MALE\"" +
                "}";
        RequestHandler handler = new RequestHandler();
        handler.acceptRequest(json);
    }
}
