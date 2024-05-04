package com.sakibul.movie_application.application;

import com.sakibul.movie_application.entities.Movie;
import com.sakibul.movie_application.entities.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testSearchMoviesByTitle() {
        MovieApplication movieApplication = new MovieApplication();
        String title = "The Shawshank Redemption";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApplication.addMovie(title, cast, category, releaseDate, budget);

        String searchTerm = "Shawshank";
        List<Movie> results = movieApplication.searchMovies(searchTerm);
        assertEquals(1, results.size());
        Movie movie = results.get(0);
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testSearchMoviesByCast() {
        MovieApplication movieApplication = new MovieApplication();
        String title = "The Shawshank Redemption";
        List<String> cast = List.of("Tim Robbins", "Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApplication.addMovie(title, cast, category, releaseDate, budget);

        String searchTerm = "Freeman";
        List<Movie> results = movieApplication.searchMovies(searchTerm);
        assertEquals(1, results.size());
        Movie movie = results.get(0);
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testSearchMoviesByCategory() {
        MovieApplication movieApplication = new MovieApplication();
        String title = "The Shawshank Redemption";
        List<String> cast = List.of("Tim Robbins", "Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApplication.addMovie(title, cast, category, releaseDate, budget);

        String searchTerm = "Drama";
        List<Movie> results = movieApplication.searchMovies(searchTerm);
        assertEquals(1, results.size());
        Movie movie = results.get(0);
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testAddToFavorites() {
        MovieApplication movieApplication = new MovieApplication();

        String email = "shawon@gmail.com";
        movieApplication.registerUser(email);
        User user = movieApplication.getUserDetails(email);

        String title = "The Shawshank Redemption";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;
        Movie movie = new Movie(title, cast, category, releaseDate, budget);
        movieApplication.addMovie(title, cast, category, releaseDate, budget);

        movieApplication.addToFavorites(user, movie);

        assertNotNull(user.getFavourites());
        assertTrue(user.getFavourites().contains(movie));
    }
}
