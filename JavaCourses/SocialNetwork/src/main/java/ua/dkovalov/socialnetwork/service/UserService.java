package ua.dkovalov.socialnetwork.service;

import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.request.CreateUserRequest;

public class UserService {
    public static void createUser(CreateUserRequest request) {
        //TODO: refer to validator: no such user already exists;
        //TODO: user who are submitting request has admin role
        UserDAO.saveUser(request.getUser());
    }

    public static void deleteUser() {

    }
}
