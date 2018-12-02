package com.tyrell.replicant.user.service;

import com.tyrell.replicant.user.service.model.User;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private static Gson gson = new Gson();

    @Autowired
    private IUserService userServiceImpl;

    @Test
    public void testRetrieveUsers() throws InterruptedException {
        // given
        String expectedFirstName = "Donald";
        String expectedLastName = "Trump";
        String expectedEmail = "princess-sparkly-rainbow-unicorn@gmail.com";

        // when
        User actual = userServiceImpl.retrieveUsers().get(0);

        // then
        String actualFirstName = actual.getFirstName();
        String actualLastName = actual.getLastName();
        String actualEmail = actual.getEmail();

        assertEquals(expectedFirstName, actualFirstName);
        assertEquals(expectedLastName, actualLastName);
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testPersistUser_whenUserIsValid() throws InterruptedException, JSONException {
        // given
        int initialCount = userServiceImpl.retrieveUsers().size();
        int expected = initialCount + 1;

        // and
        User user = new User();
        String expectedFirstName = "Alan";
        String expectedLastName = "Sugar";
        String expectedEmail = "ams@you-are-having-a-laugh.com";

        // and
        user.setFirstName(expectedFirstName);
        user.setLastName(expectedLastName);
        user.setEmail(expectedEmail);
        JSONObject userJson = new JSONObject(gson.toJson(user).toString());

        // when
        userServiceImpl.persistUser(userJson);

        // then
        int actual = userServiceImpl.retrieveUsers().size();
        assertEquals(expected, actual);
    }

    @Test(expected = UserException.class)
    public void testPersistUser_whenUserIsNotValid() throws InterruptedException, JSONException {
        // given
        int initialCount = userServiceImpl.retrieveUsers().size();
        int expected = initialCount + 1;

        // and
        User user = new User();
        String expectedFirstName = "Alan";
        String expectedLastName = "Sugar";
        String expectedEmail = "obviously not a valiid email address!!!!";

        // and
        user.setFirstName(expectedFirstName);
        user.setLastName(expectedLastName);
        user.setEmail(expectedEmail);
        JSONObject userJson = new JSONObject(gson.toJson(user).toString());

        // when
        userServiceImpl.persistUser(userJson);

        // then
        // nothing to do here!
    }

    @Test
    public void testDeleteUser_whenUserExists() throws InterruptedException, JSONException {
        // given
        User user = new User();
        String existingUser = "clive@science-of-cambridge.com";
        user.setEmail(existingUser);
        JSONObject userJson = new JSONObject(gson.toJson(user).toString());
        userServiceImpl.persistUser(userJson);

        // and
        int initialCount = userServiceImpl.retrieveUsers().size();
        int expected = initialCount - 1;

        // when
        userServiceImpl.deleteUser(existingUser);

        // then
        int actual = userServiceImpl.retrieveUsers().size();
        assertEquals(expected, actual);
    }

    @Test(expected = UserException.class)
    public void testDeleteUser_whenUserDoesNotExist() throws InterruptedException, JSONException {
        // given
        int initialCount = userServiceImpl.retrieveUsers().size();
        String idOfNonExistingUser = "clearly this is does not exist!!!";

        // when
        userServiceImpl.deleteUser(idOfNonExistingUser);

        // then
        // nothing to do here!!
    }

    @Test
    public void testUpdateUser_whenUserExists() throws InterruptedException, JSONException {
        // given
        int initialCount = userServiceImpl.retrieveUsers().size();
        String expectedJobTitle = "pheasant plucker";
        String id = "princess-sparkly-rainbow-unicorn@gmail.com";
        User user = userServiceImpl.retrieveUsers().get(0);
        user.setJobTitle(expectedJobTitle);

        // when
        userServiceImpl.updateUser(id, user);

        // then
        int finalCount = userServiceImpl.retrieveUsers().size();
        assertEquals(initialCount, finalCount);

        // and
        User updatedUser =  userServiceImpl.retrieveUsers().get(0);
        String actualJobTitle = updatedUser.getJobTitle();
        assertEquals(expectedJobTitle, actualJobTitle);
    }

    @Test(expected = UserException.class)
    public void testUpdateUser_whenUserDoesNotExist() throws InterruptedException, JSONException {
        // given
        String expectedJobTitle = "pheasant plucker";
        String id = "clearly this is does not exist!!!";
        User user = userServiceImpl.retrieveUsers().get(0);
        user.setJobTitle(expectedJobTitle);

        // when
        userServiceImpl.updateUser(id, user);

        // and
        // nothing to do here!!
    }

}
