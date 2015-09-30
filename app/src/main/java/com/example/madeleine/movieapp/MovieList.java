package com.example.madeleine.movieapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Madeleine on 30.09.2015.
 */
public class MovieList {

    @JsonProperty("Search")
    private List<Movie> movies;

    @JsonProperty("Response")
    private String response;

    @JsonProperty("Error")
    private String error;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
