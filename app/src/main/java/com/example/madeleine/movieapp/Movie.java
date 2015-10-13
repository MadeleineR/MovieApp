package com.example.madeleine.movieapp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Madeleine on 23.09.2015.
 */
public class Movie {

    private String Title;
    private String Year;
    private String id;
    private String Type;
    private String Runtime;
    private String Poster;
    private String rated;
    private String released;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String awards;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String response;
    private String country;

    public Movie() {}

    @JsonProperty("Runtime")
    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        this.Runtime = runtime;
    }

    @JsonProperty("Poster")
    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    @JsonProperty("Year")
    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    @JsonProperty("imdbID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    @JsonProperty("Rated")
    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    @JsonProperty("Released")
    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    @JsonProperty("Genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @JsonProperty("Director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @JsonProperty("Plot")
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @JsonProperty("Writer")
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @JsonProperty("Language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("Awards")
    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @JsonProperty("Metascore")
    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    @JsonProperty("imdbRating")
    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @JsonProperty("Response")
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @JsonProperty("imdbVotes")
    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    @JsonProperty("Actors")
    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}