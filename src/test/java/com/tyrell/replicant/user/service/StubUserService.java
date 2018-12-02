package com.tyrell.replicant.user.service;

import com.tyrell.replicant.user.service.model.User;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("testUserService")
public class StubUserService implements IUserService {

    @Override
    public List<User> retrieveUsers() {
        return createUserList();
    }

    @Override
    public User findUserById(String id) {
        return createUser();
    }

    @Override
    public void persistUser(JSONObject userJson) {
    }

    @Override
    public void updateUser(String id, User user) {
    }

    @Override
    public void deleteUser(String id) {
    }

    private List<User> createUserList() {
        List<User> users = new ArrayList<>();
        users.add(createUser());
        return users;
    }

    private User createUser() {
        User fred = new User();
        fred.setFirstName("Fred");
        fred.setEmail("some.email@email.com");
        return fred;
    }
}
