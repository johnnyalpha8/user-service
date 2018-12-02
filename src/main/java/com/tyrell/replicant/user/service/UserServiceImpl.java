package com.tyrell.replicant.user.service;

import com.google.gson.Gson;
import com.tyrell.replicant.user.service.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements IUserService {

    private static final String FIRST_NAME_KEY = "firstName";
    private static final String LAST_NAME_KEY = "lastName";
    private static final String EMAIL_KEY = "email";
    private static final String PHONE_NUMBER_KEY = "phoneNumber";
    private static final String JOB_TITLE_KEY = "jobTitle";
    private static final String ADDRESS_LINE_1_KEY = "addressLine1";
    private static final String ADDRESS_LINE_2_KEY = "addressLine2";
    private static final String TOWN_KEY = "town";
    private static final String COUNTY_KEY = "county";
    private static final String POST_CODE_KEY = "postCode";
    private static final String COUNTRY_KEY = "country";

    @Autowired
    private MongoOperations mongoTemplate;

    @Override
    public List<User> retrieveUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public void persistUser(JSONObject userJson) {
        Gson gson = new Gson();
        User user = gson.fromJson(userJson.toString(), User.class);
        mongoTemplate.save(user);
    }

    @Override
    public void updateUser(String id, User user) {
        Query searchQuery = new Query(Criteria.where(EMAIL_KEY).is(id));
        if (mongoTemplate.findOne(searchQuery, User.class) !=null) {
            Update update = new Update();
            update.set(FIRST_NAME_KEY, user.getSomeField());

            mongoTemplate.findAndModify(searchQuery, update, User.class);
        } else {
            //do something
        }
    }

    @Override
    public void deleteUser(String id) {
        Query searchQuery = new Query(Criteria.where(EMAIL_KEY).is(id));
        if (mongoTemplate.findOne(searchQuery, User.class) !=null) {
            mongoTemplate.remove(searchQuery, User.class);
        } else {
            //do something
        }
    }
}
