package com.tyrell.replicant.user.service;

import com.tyrell.replicant.user.service.model.User;
import org.json.JSONObject;

import java.util.List;

public interface IUserService {

    List<User> retrieveUsers();

    User findUserById(String id);

    void persistUser(JSONObject userJson);

    void updateUser(String id, User user);

    void deleteUser(String id);

}
