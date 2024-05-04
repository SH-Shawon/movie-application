package com.sakibul.movie_application.entitys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {
    @Test
    public void testCreateUser() {
        User user = new User("sakibul@gmail.com");
        assertEquals("sakibul@gmail.com", user.getEmail());
    }
}
