package ua.dkovalov.socialnetwork.request;

import java.io.IOException;

public abstract class AbstractRequest {
    protected String submitter;
    protected String requestMessage;

    public AbstractRequest(String submitter, String requestMessage) {
        this.submitter = submitter;
        this.requestMessage = requestMessage;
    }

    public String getSubmitter() {
        return submitter;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    //TODO: handle IOException
    public abstract void parseRequest() throws IOException;

    public abstract void process();
}
