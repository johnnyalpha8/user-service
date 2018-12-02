package com.tyrell.replicant.user.service;

import com.tyrell.replicant.user.service.model.User;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserController {

    @Autowired
    private IUserService userServiceImpl;

    @ApiOperation(value = "Returns available users from the Mongo database.")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userServiceImpl.retrieveUsers(), OK);
    }

    @ApiOperation(value = "Persists user to the Mongo database.")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> postUser(@RequestBody String body) throws JSONException {
        JSONObject userJson = new JSONObject(body);
        userServiceImpl.persistUser(userJson);
        Gson gson = new Gson();
        User user = gson.fromJson(userJson.toString(), User.class);
        return new ResponseEntity<>(user, CREATED);
    }

    @ApiOperation(value = "Deletes user from the Mongo database.")
    @RequestMapping(value = "/user/{email:.+}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("email") String email) throws JSONException {
        userServiceImpl.deleteUser(email);
        return new ResponseEntity<>(OK);
    }

    @ApiOperation(value = "Updates user in the Mongo database.")
    @RequestMapping(value = "/user/{email:.+}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody String body) throws JSONException {
        JSONObject userJson = new JSONObject(body);
        Gson gson = new Gson();
        User user = gson.fromJson(userJson.toString(), User.class);
        userServiceImpl.updateUser(email, user);
        return new ResponseEntity<>(user, OK);
    }

}
