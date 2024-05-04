package com.sakibul.movie_application.entitys;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUser {
    @Test
    public void testCreateUser() {
        User user = new User("sakibul@gmail.com");
        assertEquals("sakibul@gmail.com", user.getEmail());
    }

    @Test
    public void testAddToFavorites() {
        User user = new User("sakibul@gmail.com");

        String title = "The Shawshank Redemption";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;
        Movie movie = new Movie(title, cast, category, releaseDate, budget);

        user.setFavourites(movie);

        assertEquals(1, user.getFavourites().size());
        assertTrue(user.getFavourites().contains(movie));
    }
}
