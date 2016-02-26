package ua.dkovalov.socialnetwork.service;

import ua.dkovalov.socialnetwork.dao.UserDAO;
import ua.dkovalov.socialnetwork.request.CreateUserRequest;

public class UserService {
    public static void createUser(CreateUserRequest request) {
        //TODO: refer to validator
        //TODO: refer to DAO layer for insertion
        UserDAO.saveUser(request.getUser());
    }

    public static void deleteUser() {

    }
}
