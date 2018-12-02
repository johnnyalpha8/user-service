package com.tyrell.replicant.user.service;

import com.tyrell.replicant.user.service.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.tyrell.replicant.user.service.utils.InitialDataLoaderUtils.convertCSVFileToUserList;

@Component
public class InitialDataLoader implements ApplicationRunner {

    @Value("${initial.user.data}")
    private String csvFile;

    @Autowired
    private IUserService userServiceImpl;

    public void run(ApplicationArguments args) throws IOException {
        List<User> users = convertCSVFileToUserList(csvFile);
        for (User user : users) {
            userServiceImpl.persistUser(new JSONObject(user));
        }
    }

}
