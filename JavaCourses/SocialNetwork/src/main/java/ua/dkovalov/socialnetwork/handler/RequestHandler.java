package ua.dkovalov.socialnetwork.handler;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import ua.dkovalov.socialnetwork.request.AbstractRequest;
import ua.dkovalov.socialnetwork.handler.util.RequestMapper;

//TODO: use markers in debug logging
public class RequestHandler {
    private static Logger logger = LogManager.getLogger(RequestHandler.class.getName());

    public void acceptRequest(String messageBody) throws IOException {
        logger.entry();
        logger.debug("Body of the received message:\n" + messageBody);
        AbstractRequest request = RequestMapper.mapRequest(messageBody);
        request.process();
        logger.exit();
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
