package ua.dkovalov.socialnetwork.handler;

import ua.dkovalov.socialnetwork.request.AbstractRequest;
import ua.dkovalov.socialnetwork.handler.util.RequestMapper;

import java.io.IOException;

//TODO: configure log4j
//TODO: add logging everywhere
public class RequestHandler {
    public void acceptRequest(String messageBody) throws IOException {
        AbstractRequest request = RequestMapper.mapRequest(messageBody);
        request.process();
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
