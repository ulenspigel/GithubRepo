package ua.dkovalov.socialnetwork.handler.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.dkovalov.socialnetwork.request.*;
import java.io.IOException;

public class RequestMapper {
    //TODO: handle IOException
    public static AbstractRequest mapRequest(String messageBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //TODO: handle enum matching error
        MessageFields fields = mapper.readValue(messageBody, MessageFields.class);
        AbstractRequest request = null;
        switch (fields.getType()) {
            case CREATE_USER: request = new CreateUserRequest(fields.getSubmitter(), fields.getRequest().toString());
                              break;
            case DELETE_USER: break;
        }
        request.parseRequest();
        return request;
    }

    private enum RequestType {
        CREATE_USER, DELETE_USER, CREATE_POST, DELETE_POST, SEARCH_POST, ADD_COMMENT, DELETE_COMMENT
    }

    private static class MessageFields {
        private RequestType type;
        private String submitter;
        private JsonNode request;

        public MessageFields() {
        }

        public void setType(String type) {
            this.type = RequestType.valueOf(type);
        }

        public void setSubmitter(String submitter) {
            this.submitter = submitter;
        }

        public void setRequest(JsonNode request) {
            this.request = request;
        }

        public RequestType getType() {
            return type;
        }

        public String getSubmitter() {
            return submitter;
        }

        public JsonNode getRequest() {
            return request;
        }
    }
}
