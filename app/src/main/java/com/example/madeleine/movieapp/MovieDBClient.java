package com.example.madeleine.movieapp;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

/**
 * Created by Madeleine on 30.09.2015.
 */
@Rest(rootUrl = "http://www.omdbapi.com/", converters = { MappingJackson2HttpMessageConverter.class })
public interface MovieDBClient {

    @Get("?i={id}")
    Movie getMovie(String id);

}
