package com.sakibul.movie_application.application;

import com.sakibul.movie_application.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MovieApplication {
    private List<User> users;

    public MovieApplication() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
    }

    public void registerUser(String email) {
        users.add(new User(email));
    }

    public User getUserDetails(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

}
