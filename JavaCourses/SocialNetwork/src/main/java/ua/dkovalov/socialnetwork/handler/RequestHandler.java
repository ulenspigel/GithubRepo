package ua.dkovalov.socialnetwork.handler;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ua.dkovalov.socialnetwork.util.DAOUtil;
import ua.dkovalov.socialnetwork.request.AbstractRequest;
import ua.dkovalov.socialnetwork.handler.util.RequestMapper;

public class RequestHandler {
    private static final Logger logger = LogManager.getLogger(RequestHandler.class);

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
        try (BufferedReader jsonFile = new BufferedReader(new FileReader(RequestHandler.class.getResource("/createPost.json").getPath()))) {
            while ((jsonRow = jsonFile.readLine()) != null) {
                json.append(jsonRow);
            }
        }
        RequestHandler handler = new RequestHandler();
        try {
            handler.acceptRequest(json.toString());
        } finally {
            //TODO: move to the proper place in code (method stopMessagesProcessing that terminates database and JMS connections etc.)
            DAOUtil.closeSessionFactory();
        }
    }
}
