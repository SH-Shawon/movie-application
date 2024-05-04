package com.sakibul.movie_application.consoleOperations;

import com.sakibul.movie_application.application.MovieApp;
import com.sakibul.movie_application.entities.Movie;
import com.sakibul.movie_application.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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

    public void registerUser() throws IOException {
        System.out.print("Enter your email address: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String email = bufferedReader.readLine();

        boolean flag=true;
        while (flag) {
            if (!email.trim().equals("")) {
                User user = new User(email);
                movieApp.setUsers(user);
                this.userMenu(user);
                flag=false;
            } else {
                System.out.println("Insert a valid email");
                this.registerUser();
            }
        }
    }

    private void userMenu(User user) throws IOException {
        int choice;

        do {
            System.out.println("\nUser Menu:");
            System.out.println("1. Search Movies");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchMovies(user);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void searchMovies(User user) throws IOException {
        System.out.print("Enter title, cast or category to search: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String searchTerm = bufferedReader.readLine();

        List<Movie> results = movieApp.searchMovies(searchTerm);
        if (results.isEmpty()) {
            System.out.println("No movies found");
        } else {
            System.out.println("\nSearch Results:");
            int serialNo = 1;
            for (Movie movie : results) {
                System.out.println(serialNo++ + ". " + movie.getTitle());
            }
        }
    }

}
