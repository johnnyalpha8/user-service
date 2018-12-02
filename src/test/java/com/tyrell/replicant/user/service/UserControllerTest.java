package com.tyrell.replicant.user.service;

import com.tyrell.replicant.user.service.model.User;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static Gson gson = new Gson();

    @LocalServerPort
    private int port;

    @Autowired
    IUserService testUserService;

    @Autowired
    UserController controller;

    private static final String REQUEST_PATH = "/user";
    private static final String FORWARD_SLASH = "/";

    @Before
    public void setUp() {
        String fieldToReplace = "userServiceImpl";
        setField(controller, fieldToReplace, testUserService);
    }

    @Test
    public void testGetUsers() {
        // given
        int expectedStatusCode = 200;
        List<User> expectedUsers = testUserService.retrieveUsers();

        // when
        Response response = RestAssured.given().port(port).when().get(REQUEST_PATH);

        // then
        String responseBodyAsString = response.getBody().asString();
        List<User> actualUsers = Arrays.asList(gson.fromJson(responseBodyAsString, User[].class));

        // and
        assertEquals(expectedStatusCode, response.getStatusCode());
        assertEquals(expectedUsers.size(), actualUsers.size());
    }

    @Test
    public void testPostUser() {
        // given
        int expectedStatusCode = 201;
        String expectedEmail = "hilary.clinton@dodgy-server.com";

        // and
        User expectedUser = new User();
        expectedUser.setEmail(expectedEmail);

        // when
        Response response = RestAssured.given().body(expectedUser).port(port).when().post(REQUEST_PATH);

        // then
        String responseBodyAsString = response.getBody().asString();
        User actualUser = gson.fromJson(responseBodyAsString, User.class);

        assertEquals(expectedStatusCode, response.getStatusCode());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testDeleteUser() {
        // given
        int expectedStatusCode = 200;
        String id = "princess-sparkly-rainbow-unicorn@gmail.com";

        // when
        Response response = RestAssured.given().port(port).when().delete(REQUEST_PATH + FORWARD_SLASH + id);

        // then
        String responseBodyAsString = response.getBody().asString();

        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    public void testUpdateUser() throws JSONException {
        // given
        int expectedStatusCode = 200;
        String expectedNewJobTitle = "Starbucks Barista";
        String id = "hilary.clinton@dodgy-server.com";

        // and
        User user = new User();
        user.setEmail(id);
        user.setJobTitle(expectedNewJobTitle);
        JSONObject userJson = new JSONObject(gson.toJson(user).toString());

        // when
        Response response = RestAssured.given().body(userJson.toString()).port(port).when().put(REQUEST_PATH + FORWARD_SLASH + id);

        // then
        String responseBodyAsString = response.getBody().asString();
        User actualUser = gson.fromJson(responseBodyAsString, User.class);

        assertEquals(expectedStatusCode, response.getStatusCode());
        assertEquals(expectedNewJobTitle, actualUser.getJobTitle());
    }

}