package ua.dkovalov.socialnetwork.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.request.AbstractRequest;

public abstract class AbstractRequestValidator<T> {
    protected static Logger logger;
    protected AbstractRequest<T> request;

    protected AbstractRequestValidator(Class classForLogging) {
        logger = LogManager.getLogger(classForLogging);
    }

    protected void throwValidationError(String errorMessage) {
        logger.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }

    public void validateSubmitterIsAdmin() {
        if (!UserDAO.isUserAdmin(request.getSubmitter())) {
            throwValidationError("Request submitter " + request.getSubmitter() + " do not have admin privileges");
        }
    }

    public AbstractRequest<T> getRequest() {
        return request;
    }

    public void setRequest(AbstractRequest<T> request) {
        this.request = request;
    }
}
