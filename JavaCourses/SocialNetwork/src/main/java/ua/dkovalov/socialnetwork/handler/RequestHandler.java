package ua.dkovalov.socialnetwork.handler;

import ua.dkovalov.socialnetwork.request.CreateUserRequest;
import ua.dkovalov.socialnetwork.service.UserService;
import ua.dkovalov.socialnetwork.handler.util.RequestMapper;

import java.io.IOException;

//TODO: configure log4j
//TODO: add logging everywhere
//TODO: implement common format for JSON requests
public class RequestHandler {
    public void acceptRequest(String messageBody) throws IOException {
        RequestMapper requestMapper = new RequestMapper(messageBody);
        CreateUserRequest createUserRequest = requestMapper.mapRequest();
        UserService.createUser(createUserRequest);
    }

    public static void main(String[] args) throws IOException {
        String json =
                "{\n" +
                        "\t\"type\":\"CREATE_USER\",\n" +
                        "\t\"submitter\":\"admin\",\n" +
                        "\t\"request\":{\n" +
                        "\t\t\t\t\"firstName\":\"First\",\n" +
                        "\t\t\t\t\"lastName\":\"Last\",\n" +
                        "\t\t\t\t\"nickname\":\"nickname\",\n" +
                        "\t\t\t\t\"gender\":\"MALE\"\n" +
                        "              }\n" +
                        "}";
        RequestHandler handler = new RequestHandler();
        handler.acceptRequest(json);
    }
}
