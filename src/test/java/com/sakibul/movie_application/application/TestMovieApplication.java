package com.sakibul.movie_application.application;

import com.sakibul.movie_application.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestMovieApplication {
    @Test
    public void testRegisteringUser(){
        MovieApplication movieApplication = new MovieApplication();
        String email = "shawon@gmail.com";
        movieApplication.registerUser(email);
        User user = movieApplication.getUserDetails(email);
        assertNotNull(user);
        assertEquals(email,user.getEmail());
    }
}
