package ua.dkovalov.socialnetwork.handler.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.dkovalov.socialnetwork.configuration.AppConfig;
import ua.dkovalov.socialnetwork.request.*;

public class RequestMapper {
    private static final Logger logger = LogManager.getLogger(RequestMapper.class);

    public static AbstractRequest mapRequest(String messageBody) {
        ObjectMapper mapper = new ObjectMapper();
        MessageFields fields = null;
        try {
            fields = mapper.readValue(messageBody, MessageFields.class);
        } catch (IOException ioe) {
            logger.error("Unable to recognize request type for message:\n" + messageBody);
            throw new RuntimeException(ioe);
        }
        logger.info("Request type recognized: " + fields.getType() + ". Submitter is: " + fields.getSubmitter());
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AbstractRequest request = (AbstractRequest) applicationContext.getBean(fields.getType().getRequestClass());
        request.setSubmitter(fields.getSubmitter());
        request.setSubmissionTime(fields.getSubmissionTime());
        request.setRequestMessage(fields.getRequest().toString());
        try {
            request.parseRequest();
        } catch (IOException ioe) {
            logger.error("Unable to parse request:\n" + request.getRequestMessage());
            throw new UnsupportedOperationException(ioe);
        }
        return request;
    }

    private static enum RequestType {
        CREATE_USER(CreateUserRequest.class),
        DELETE_USER(DeleteUserRequest.class),
        CREATE_POST(CreatePostRequest.class),
        DELETE_POST(DeletePostRequest.class);
        //, SEARCH_POST, ADD_COMMENT, DELETE_COMMENT

        private Class requestClass;

        private RequestType(Class requestClass) {
            this.requestClass = requestClass;
        }

        public Class getRequestClass() {
            return requestClass;
        }
    }

    private static class MessageFields {
        private RequestType type;
        private String submitter;
        private DateTime submissionTime;
        private JsonNode request;

        public MessageFields() {
        }

        public void setType(String type) {
            this.type = RequestType.valueOf(type);
        }

        public void setSubmitter(String submitter) {
            this.submitter = submitter;
        }

        public void setSubmissionTime(String jsonDate) {
            submissionTime = DateTime.parse(jsonDate, ISODateTimeFormat.dateTime());
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

        public DateTime getSubmissionTime() {
            return submissionTime;
        }

        public JsonNode getRequest() {
            return request;
        }
    }
}
