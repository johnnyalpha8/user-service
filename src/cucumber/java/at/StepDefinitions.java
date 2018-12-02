package at;

import com.tyrell.replicant.user.service.Application;
import com.tyrell.replicant.user.service.model.User;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StepDefinitions {

    private static Gson gson = new Gson();

    @LocalServerPort
    private int port;

    @Autowired
    private MongoOperations mongoTemplate;

    private static final String REQUEST_PATH = "/user";
    private static final String FORWARD_SLASH = "/";
    private static Response lastResponse;

    @Given("^a running environment$")
    public void a_running_environment() throws Throwable {
        RestAssured.given().port(port).get();
    }

    @When("^I make a request for all users$")
    public void i_make_a_request_for_all_users() throws Throwable {
        lastResponse = RestAssured.given().port(port).when().get(REQUEST_PATH);
    }

    @Then("^the following users should be returned:$")
    public void the_following_users_should_be_returned(List<User> expectedUsers) throws Throwable {
        List<User> actualUsers = Arrays.asList(lastResponse.getBody().as(User[].class));
        for (int index = 0; index < expectedUsers.size(); index++) {
            User actual = actualUsers.get(index);
            User expected = expectedUsers.get(index);
            assertEquals(expected, actual);
        }
    }

    @Then("^the following users should be included:$")
    public void the_following_users_should_be_included(List<User> expectedUsers) throws Throwable {
        boolean userIsIncluded = false;
        List<User> actualUsers = Arrays.asList(lastResponse.getBody().as(User[].class));
        for (int i = 0; i < expectedUsers.size(); i++) {
            User expected = expectedUsers.get(i);
            userIsIncluded = actualUsers.contains(expected);
        }
        assertTrue(userIsIncluded);
    }


    @Given("^I make requests to create the following users:$")
    public void i_make_requests_to_create_the_following_users(List<User> users) throws Throwable {
        for (int index = 0; index < users.size(); index++) {
            User user = users.get(index);
            JSONObject userJson = new JSONObject(gson.toJson(user).toString());
            lastResponse = RestAssured.given().body(userJson.toString()).port(port).when().post(REQUEST_PATH);
        }
    }

    @Given("^I make requests to delete the following users:$")
    public void i_make_requests_to_delete_the_following_users(List<User> users) throws Throwable {
        for (int index = 0; index < users.size(); index++) {
            User user = users.get(index);
            JSONObject userJson = new JSONObject(gson.toJson(user).toString());
            lastResponse = RestAssured.given().body(userJson.toString()).port(port).when().delete(REQUEST_PATH + FORWARD_SLASH + user.getEmail());
        }
    }

    @Given("^I make a request to update user with id \"([^\"]*)\" as follows:$")
    public void i_make_a_request_to_update_user_with_id_as_follows(String id, List<User> users) throws Throwable {
        User targetUser = users.get(0);
        JSONObject userJson = new JSONObject(gson.toJson(targetUser).toString());
        lastResponse = RestAssured.given().body(userJson.toString()).port(port).when().put(REQUEST_PATH + FORWARD_SLASH + id);
    }

}