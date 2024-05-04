package com.sakibul.movie_application.consoleOperations;

import com.sakibul.movie_application.application.MovieApp;
import com.sakibul.movie_application.entities.Movie;
import com.sakibul.movie_application.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

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
            System.out.println("2. See Movie Details by Title");
            System.out.println("3. View Personal Details");
            System.out.println("4. Search movies that are among your favorite list");
            System.out.println("5. See movies that you added as favorite");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchMovies(user);
                    break;
                case 2:
                    seeMovieDetailsByTitle(user);
                    break;
                case 3:
                    showUserDetails(user);
                    break;
                case 4:
                    searchFavoriteList(user);
                    break;
                case 5:
                    seeFavoriteList(user);
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
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nDo you want to add a movie to your favorites (y/n)? ");
            String addToFavourites = bufferedReader.readLine().toLowerCase();
            if (addToFavourites.equals("y")) {
                System.out.print("Enter the serial number of the movie to add: ");

                int movieChoice = scanner.nextInt() - 1; // Adjust for 0-based indexing
                System.out.println("you choose:"+ movieChoice);
                if (movieChoice >= 0 && movieChoice < results.size()) {
                    movieApp.addToFavorites(user, results.get(movieChoice));
                    System.out.println("Movie added to favorites successfully!");
                } else {
                    System.out.println("Invalid movie choice.");
                }
            }
        }
    }



    private void seeMovieDetailsByTitle(User user) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie title (or leave blank to search all movies): ");
        String title = scanner.nextLine();

        List<Movie> movies;
        if (title.isEmpty()) {
            movies = movieApp.getAllMovies();
        } else {
            Movie movie=movieApp.getMovieDetails(title);
            if (movie==null){
                movies= Collections.emptyList();
            }else {
                movies=List.of(movie);
            }
        }

        if (movies.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }

        System.out.println("\nMovie List:");
        int serialNo = 1;
        for (Movie movie : movies) {
            System.out.println(serialNo++ + ". " + movie.getTitle());
        }

        System.out.print("\nEnter the serial number of the movie you want to see details (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= movies.size()) {
            Movie selectedMovie = movies.get(choice - 1);
            System.out.println("\nMovie Details:");
            System.out.println("Title: " + selectedMovie.getTitle());
            System.out.println("Cast: " + String.join(", ", selectedMovie.getCast()));
            System.out.println("Category: " + selectedMovie.getCategory());
            System.out.println("Release Date: " + selectedMovie.getReleaseDate());
            System.out.println("Budget: $" + selectedMovie.getBudget());
            String addToFavourites;
            do {
                System.out.print("\nDo you want to add this movie to your favorites (y/n)? ");
                addToFavourites = scanner.nextLine().toLowerCase();
            } while (!addToFavourites.equals("y") && !addToFavourites.equals("n"));

            if (addToFavourites.equals("y")) {
                movieApp.addToFavorites(user,selectedMovie);
            }
        } else {
            System.out.println("Invalid choice or cancelled.");
        }
    }


    private void showUserDetails(User user) {
        System.out.println("\nUser Details:");
        System.out.println("Email: " + user.getEmail());

        System.out.println("\nFavorites:");
        List<Movie> favourites = user.getFavourites();
        if (favourites.isEmpty()) {
            System.out.println("You don't have any favorite movies yet.");
        } else {
            int serialNo = 1;
            for (Movie movie : favourites) {
                System.out.println(serialNo++ + ". " + movie.getTitle());
            }
        }
    }


    private void searchFavoriteList(User user) {
        List<Movie> favorites = user.getFavourites();
        if (favorites.isEmpty()) {
            System.out.println("You don't have any favorite movies yet.");
            return;
        }

        System.out.print("Enter search term (title, cast, or category): ");
        Scanner scanner = new Scanner(System.in);
        String searchTerm = scanner.nextLine();

        List<Movie> filteredFavorites = new ArrayList<>();
        for (Movie movie : favorites) {
            if (movie.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    movie.getCast().stream().anyMatch(castMember -> castMember.toLowerCase().contains(searchTerm.toLowerCase())) ||
                    movie.getCategory().stream().anyMatch(category -> category.toLowerCase().contains(searchTerm.toLowerCase()))) {
                filteredFavorites.add(movie);
            }
        }

        if (filteredFavorites.isEmpty()) {
            System.out.println("No matching favorites found for your search term.");
        } else {
            System.out.println("\nSearch Results:");
            int serialNo = 1;
            for (Movie movie : filteredFavorites) {
                System.out.println(serialNo++ + ". " + movie.getTitle());
            }
        }
    }


    private void seeFavoriteList(User user) {
        System.out.println("\nFavorites:");
        List<Movie> favourites = user.getFavourites();
        if (favourites.isEmpty()) {
            System.out.println("You don't have any favorite movies yet.");
        } else {
            int serialNo = 1;
            for (Movie movie : favourites) {
                System.out.println(serialNo++ + ". " + movie.getTitle());
            }
        }
    }


}
