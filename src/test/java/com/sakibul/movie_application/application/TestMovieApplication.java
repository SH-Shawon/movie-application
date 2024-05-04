package com.sakibul.movie_application.application;

import com.sakibul.movie_application.entities.Movie;
import com.sakibul.movie_application.entities.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testAddMovie() {
        MovieApplication movieApplication = new MovieApplication();
        String title = "The Shawshank Redemption";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApplication.addMovie(title, cast, category, releaseDate, budget);

        List<Movie> allMovies = movieApplication.getAllMovies();
        assertEquals(1, allMovies.size());
        Movie movie = allMovies.get(0);
        assertEquals(title, movie.getTitle());
        assertEquals(cast, movie.getCast());
        assertEquals(category, movie.getCategory());
        assertEquals(releaseDate, movie.getReleaseDate());
        assertEquals(budget, movie.getBudget());
    }
}