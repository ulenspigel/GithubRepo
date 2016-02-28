package ua.dkovalov.socialnetwork.handler.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.dkovalov.socialnetwork.request.CreateUserRequest;

import java.io.IOException;
import java.util.Map;

public class RequestMapper {
    String messageBody;
    String type;
    String submitter;


    public RequestMapper(String messageBody) {
        this.messageBody = messageBody;
    }

    //TODO: handle IOException
    public CreateUserRequest mapRequest() throws IOException {
        // TODO: parse out from JSON the request type
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(messageBody, Map.class);


        /*CreateUserRequest createUserRequest = new CreateUserRequest(requestMessage);
        createUserRequest.parseRequest();
        return createUserRequest;*/
        return null;
    }

    private enum RequestType {
        CREATE_USER, DELETE_USER
    }
}
