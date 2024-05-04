package com.sakibul.movie_application.consoleOperations;

import com.sakibul.movie_application.application.MovieApp;
import com.sakibul.movie_application.entities.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandLineOperations {
    private MovieApp movieApp;
    public CommandLineOperations(MovieApp movieApp) {
        this.movieApp = movieApp;
    }

    public MovieApp getMovieApplication() {
        return movieApp;
    }
    public void setMovieApplication(MovieApp movieApp) {
        this.movieApp = movieApp;
    }

    public void createSampleMovies() {
        List<String> cast1 = new ArrayList<>();
        cast1.add("Tim Robbins");
        cast1.add("Morgan Freeman");
        List<String> category1 = List.of("Drama","Love");
        LocalDate releaseDate1 = LocalDate.parse("1994-10-14");
        int budget1 = 15000000;

        List<String> cast2 = List.of("Marlon Brando", "Al Pacino");
        List<String> category2 = List.of("Crime","Action");
        LocalDate releaseDate2 = LocalDate.parse("1972-03-24");
        int budget2 = 6000000;

        // Add 10 sample movies (modify details and add more as needed)
        for (int i = 1; i <= 10; i++) {
            String title = " Movie " + i;
            movieApp.addMovie(title, cast1, category1, releaseDate1.plusYears(i - 1), budget1 * i);
            movieApp.addMovie("Bangla" + title  , cast2, category2, releaseDate2.plusYears(i - 1), budget2 * i);
        }

        this.printAllMovies();

    }
    private void printAllMovies(){
        for (Movie movie : movieApp.getAllMovies()) {
            System.out.println("\nTitle: " + movie.getTitle());

            // Print cast members
            System.out.print("Cast: ");
            List<String> cast = movie.getCast();
            boolean isFirstCast = true;
            for (String castMember : cast) {
                if (isFirstCast) {
                    isFirstCast = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print(castMember);
            }
            System.out.println();

            System.out.print("Category: ");
            List<String> category = movie.getCategory();
            boolean isFirstCategory = true;
            for (String categoryItem : category) {
                if (isFirstCategory) {
                    isFirstCategory = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print(categoryItem);
            }
            System.out.println();

            System.out.println("Release Date: " + movie.getReleaseDate());
            System.out.println("Budget: $" + movie.getBudget());
        }
    }
}
