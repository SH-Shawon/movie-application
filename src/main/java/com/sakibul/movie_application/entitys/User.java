package com.sakibul.movie_application.entitys;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private List<Movie> favourites;

    public User(String email) {
        this.email = email;
        favourites = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public List<Movie> getFavourites() {
        return favourites;
    }

    public void setFavourites(Movie movie) {
        favourites.add(movie);
    }

    public void removeFromFavourite(Movie movie){
        favourites.remove(movie);
    }
}
