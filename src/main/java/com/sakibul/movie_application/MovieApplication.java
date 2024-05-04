package com.sakibul.movie_application;

import com.sakibul.movie_application.application.MovieApp;
import com.sakibul.movie_application.consoleOperations.CommandLineOperations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome to our movie app!");
		CommandLineOperations commandLineOperations = new CommandLineOperations(new MovieApp());
		commandLineOperations.createSampleMovies();
	}
}
