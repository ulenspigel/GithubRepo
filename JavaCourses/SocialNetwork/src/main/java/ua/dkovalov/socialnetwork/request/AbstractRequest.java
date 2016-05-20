package ua.dkovalov.socialnetwork.request;

import org.joda.time.DateTime;
import java.io.IOException;

public abstract class AbstractRequest<T> {
    protected String submitter;
    protected String requestMessage;
    protected DateTime submissionTime;

    public AbstractRequest(String submitter, String requestMessage, DateTime submissionTime) {
        this.submitter = submitter;
        this.requestMessage = requestMessage;
        this.submissionTime = submissionTime;
    }

    public AbstractRequest() {
    }

    public String getSubmitter() {
        return submitter;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public DateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public void setSubmissionTime(DateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    public abstract void parseRequest() throws IOException;

    public abstract void process();

    public abstract T getParsedObject();
}
