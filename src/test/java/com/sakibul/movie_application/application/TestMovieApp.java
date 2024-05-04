package com.sakibul.movie_application.application;

import com.sakibul.movie_application.entities.Movie;
import com.sakibul.movie_application.entities.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMovieApp {
    @Test
    public void testRegisteringUser(){
        MovieApp movieApp = new MovieApp();
        String email = "shawon@gmail.com";
        movieApp.registerUser(email);
        User user = movieApp.getUserDetails(email);
        assertNotNull(user);
        assertEquals(email,user.getEmail());
    }

    @Test
    public void testAddMovie() {
        MovieApp movieApp = new MovieApp();
        String title = "The Shawshank Redemption";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApp.addMovie(title, cast, category, releaseDate, budget);

        List<Movie> allMovies = movieApp.getAllMovies();
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
        MovieApp movieApp = new MovieApp();
        String title = "The Shawshank Redemption";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApp.addMovie(title, cast, category, releaseDate, budget);

        String searchTerm = "Shawshank";
        List<Movie> results = movieApp.searchMovies(searchTerm);
        assertEquals(1, results.size());
        Movie movie = results.get(0);
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testSearchMoviesByCast() {
        MovieApp movieApp = new MovieApp();
        String title = "The Shawshank Redemption";
        List<String> cast = List.of("Tim Robbins", "Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApp.addMovie(title, cast, category, releaseDate, budget);

        String searchTerm = "Freeman";
        List<Movie> results = movieApp.searchMovies(searchTerm);
        assertEquals(1, results.size());
        Movie movie = results.get(0);
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testSearchMoviesByCategory() {
        MovieApp movieApp = new MovieApp();
        String title = "The Shawshank Redemption";
        List<String> cast = List.of("Tim Robbins", "Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;

        movieApp.addMovie(title, cast, category, releaseDate, budget);

        String searchTerm = "Drama";
        List<Movie> results = movieApp.searchMovies(searchTerm);
        assertEquals(1, results.size());
        Movie movie = results.get(0);
        assertEquals(title, movie.getTitle());
    }

    @Test
    public void testAddToFavorites() {
        MovieApp movieApp = new MovieApp();

        String email = "shawon@gmail.com";
        movieApp.registerUser(email);
        User user = movieApp.getUserDetails(email);

        String title = "The Shawshank Redemption";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;
        Movie movie = new Movie(title, cast, category, releaseDate, budget);
        movieApp.addMovie(title, cast, category, releaseDate, budget);

        movieApp.addToFavorites(user, movie);

        assertNotNull(user.getFavourites());
        assertTrue(user.getFavourites().contains(movie));
    }

    @Test
    public void testRemoveFromFavorites() {
        MovieApp movieApp = new MovieApp();

        String email = "sakibul@gmail.com";
        movieApp.registerUser(email);
        User user = movieApp.getUserDetails(email);

        String title = "sakibul@gmail.com";
        List<String> cast = new ArrayList<>();
        cast.add("Tim Robbins");
        cast.add("Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate releaseDate = LocalDate.parse("1994-10-14");
        int budget = 25000000;
        Movie movie = new Movie(title, cast, category, releaseDate, budget);
        movieApp.addMovie(title, cast, category, releaseDate, budget);
        movieApp.addToFavorites(user, movie);

        assertNotNull(user.getFavourites());
        assertTrue(user.getFavourites().contains(movie));

        movieApp.removeFromFavorites(user, movie);

        assertTrue(user.getFavourites().isEmpty() || !user.getFavourites().contains(movie));
    }

    @Test
    public void testGetMovieDetails() {
        MovieApp movieApp = new MovieApp();

        String title1 = "The Shawshank Redemption";
        List<String> cast1 = new ArrayList<>();
        cast1.add("Tim Robbins");
        cast1.add("Morgan Freeman");
        List<String> category1 = List.of("Drama");
        LocalDate releaseDate1 = LocalDate.parse("1994-10-14");
        int budget1 = 25000000;

        String title2 = "The Godfather";
        List<String> cast2 = List.of("Marlon Brando", "Al Pacino");
        List<String> category2 = List.of("Crime");
        LocalDate releaseDate2 = LocalDate.parse("1972-03-24");
        int budget2 = 6000000;

        movieApp.addMovie(title1, cast1, category1, releaseDate1, budget1);
        movieApp.addMovie(title2, cast2, category2, releaseDate2, budget2);

        Movie movie1 = movieApp.getMovieDetails(title1);
        assertNotNull(movie1);
        assertEquals(title1, movie1.getTitle());

        Movie movie2 = movieApp.getMovieDetails("The Dark Knight");
        assertNull(movie2);
    }

}
