package ua.dkovalov.socialnetwork.handler;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.FileReader;
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
        StringBuilder json = new StringBuilder("");
        String jsonRow = "";
        try (BufferedReader jsonFile = new BufferedReader(new FileReader(RequestHandler.class.getResource("/createUser.json").getPath()))) {
            while ((jsonRow = jsonFile.readLine()) != null) {
                json.append(jsonRow);
            }
        }
        RequestHandler handler = new RequestHandler();
        handler.acceptRequest(json.toString());
    }
}
