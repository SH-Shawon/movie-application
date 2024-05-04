package com.sakibul.movie_application.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testRemoveFromFavorites() {
        User user = new User("sakibul@gmail.com");

        String title = "The Godfather";
        List<String> cast = List.of("Marlon Brando", "Al Pacino");
        List<String> category = List.of("Crime");
        LocalDate releaseDate = LocalDate.parse("1972-03-24");
        int budget = 6000000;
        Movie movie = new Movie(title, cast, category, releaseDate, budget);

        user.setFavourites(movie);
        user.setFavourites(movie);

        assertEquals(2, user.getFavourites().size());
        assertTrue(user.getFavourites().contains(movie));

        user.removeFromFavourite(movie);

        assertEquals(1, user.getFavourites().size());
        assertTrue(user.getFavourites().contains(movie));

        user.removeFromFavourite(movie);
        assertFalse(user.getFavourites().contains(movie));

    }
}
