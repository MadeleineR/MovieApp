package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MovieAppDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(4, "com.example.madeleine.movieapp");

        Entity movieDetailDAO = schema.addEntity("MovieDetail");
        movieDetailDAO.addIdProperty();
        movieDetailDAO.addStringProperty("year");
        movieDetailDAO.addStringProperty("type");
        movieDetailDAO.addStringProperty("runtime");
        movieDetailDAO.addStringProperty("poster");
        movieDetailDAO.addStringProperty("rated");
        movieDetailDAO.addStringProperty("released");
        movieDetailDAO.addStringProperty("genre");
        movieDetailDAO.addStringProperty("director");
        movieDetailDAO.addStringProperty("writer");
        movieDetailDAO.addStringProperty("actors");
        movieDetailDAO.addStringProperty("plot");
        movieDetailDAO.addStringProperty("language");
        movieDetailDAO.addStringProperty("awards");
        movieDetailDAO.addStringProperty("metascore");
        movieDetailDAO.addStringProperty("imdbRating");
        movieDetailDAO.addStringProperty("imdbVotes");
        movieDetailDAO.addStringProperty("response");
        movieDetailDAO.addStringProperty("country");

        Entity movie = schema.addEntity("MovieInfo");
        movie.addStringProperty("imdbID");
        movie.addStringProperty("title");
        Property detailIdProperty = movie.addLongProperty("detailId").getProperty();
        movie.addToOne(movieDetailDAO, detailIdProperty);

        Entity query = schema.addEntity("Query");
        query.addIdProperty();
        query.addStringProperty("searchstring");
        Property queryId = movie.addLongProperty("queryId").getProperty();
        query.addToMany(movie, queryId);

        new DaoGenerator().generateAll(schema, "../app/src/main/java");
    }
}
