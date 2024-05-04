package com.sakibul.movie_application.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMovie {
    @Test
    public void testCreateMovie() {
        List<String> cast = List.of("Tim Robbins", "Morgan Freeman");
        List<String> category = List.of("Drama");
        LocalDate localDate=LocalDate.of(2024,4,18);
        Movie movie = new Movie("The Shawshank Redemption", cast, category, localDate, 25000000);
        assertEquals("The Shawshank Redemption", movie.getTitle());

        assertEquals(cast, movie.getCast());
        assertEquals(category, movie.getCategory());
        assertEquals(localDate, movie.getReleaseDate());
        assertEquals(25000000, movie.getBudget());
    }
}
