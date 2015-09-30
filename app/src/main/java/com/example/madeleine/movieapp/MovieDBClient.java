package com.example.madeleine.movieapp;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madeleine on 30.09.2015.
 */
@Rest(rootUrl = "http://www.omdbapi.com/", converters = { MappingJackson2HttpMessageConverter.class })
public interface MovieDBClient {

    @Get("?i={id}")
    Movie getMovie(String id);

    @Get("?s={searchInput}")
    MovieList getMovieList(String searchInput);

}
