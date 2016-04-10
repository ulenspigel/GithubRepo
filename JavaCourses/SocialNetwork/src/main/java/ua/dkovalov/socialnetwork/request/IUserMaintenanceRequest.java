package ua.dkovalov.socialnetwork.request;

import ua.dkovalov.socialnetwork.entity.User;

public interface IUserMaintenanceRequest {
    String getSubmitter();
    User getUser();
}
