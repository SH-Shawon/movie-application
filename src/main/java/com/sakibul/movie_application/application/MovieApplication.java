package com.sakibul.movie_application.application;

import com.sakibul.movie_application.entities.Movie;
import com.sakibul.movie_application.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieApplication {
    private List<User> users;
    private List<Movie> allMovies;

    public MovieApplication() {
        users = new ArrayList<>();
        allMovies = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
    }

    public List<Movie> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(List<Movie> allMovies) {
        this.allMovies = allMovies;
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

    public void addMovie(String title, List<String> cast, List<String> category, LocalDate releaseDate, int budget) {
        allMovies.add(new Movie(title, cast, category, releaseDate, budget));
    }

    public List<Movie> searchMovies(String searchTerm) {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (movie.getTitle() != null && movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(movie);
                continue;
            }

            List<String> cast = movie.getCast();
            if (cast != null) {
                for (String item : cast) {
                    if (item.toLowerCase().contains(searchTerm.toLowerCase())) {
                        results.add(movie);
                        break;
                    }
                }
            }

            List<String> category = movie.getCategory();
            if (category != null) {
                for (String item : category) {
                    if (item.toLowerCase().contains(searchTerm.toLowerCase())) {
                        results.add(movie);
                        break;
                    }
                }
            }
        }
        return results;
    }

}
