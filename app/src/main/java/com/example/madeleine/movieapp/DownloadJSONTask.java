package com.example.madeleine.movieapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.*;

/**
 * Created by Christoph on 21.09.2015.
 */
public class DownloadJSONTask extends AsyncTask<String, Void, ArrayList<Movie>> {
    public String DEBUG_TAG = "MOVIE_SEARCH";
    private AsyncListener resultListener;
    private char getParameter;

    public DownloadJSONTask(AsyncListener listener, char getParameter) {
        this.resultListener = listener;
        this.getParameter = getParameter;
    }

    public interface AsyncListener {
        public void setResults ( ArrayList<Movie> results );
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... urls) {
        // params comes from the execute() call: params[0] is the url.
        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return null;
        }
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        resultListener.setResults(result);
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    private ArrayList<Movie> downloadUrl(String myurl) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myurl);
            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            if (getParameter=='s') {
                ArrayList results = parseMovieList(contentAsString);
                return results;
            }
            else if (getParameter == 'i') {
                ArrayList<Movie> results = parseMovie(contentAsString);
                return results;
            }
            else {
                return null;
            }

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }



    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        InputStreamReader is = new InputStreamReader(stream);
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(is);
        String read = br.readLine();

        while(read != null) {
            sb.append(read);
            read =br.readLine();
        }

        return sb.toString();
    }

    // Reads an InputStream and converts it to a String.
    public ArrayList<Movie> parseMovieList(String string) throws IOException, UnsupportedEncodingException {
        JsonParser parser = new JsonParser();
        try {
            JsonObject jo = (JsonObject) parser.parse(string);

            ArrayList<Movie> results = new ArrayList<>();
            if (jo != null) {
                JsonElement search = jo.get("Search");
                if(search != null) {
                    JsonArray jarray = search.getAsJsonArray();
                    Gson gson = new Gson();
                    if (jarray != null && jarray.isJsonArray()) {
                        int len = jarray.size();
                        for (int i = 0; i < len; i++) {
                            JsonObject o = (JsonObject) jarray.get(i);
                            Movie movie = new Movie();
                            movie.setTitle(o.get("Title").getAsString());
                            movie.setYear(o.get("Year").getAsString());
                            movie.setId(o.get("imdbID").getAsString());
                            movie.setType(o.get("Type").getAsString());
                            results.add(movie);
                        }
                    }
                }
            }

            return results;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Movie> parseMovie(String string) throws IOException, UnsupportedEncodingException {
        JsonParser parser = new JsonParser();
        try {
            JsonObject o = (JsonObject) parser.parse(string);

            ArrayList<Movie> results = new ArrayList<>();
            if (o != null) {
                Gson gson = new Gson();

                Movie movie = new Movie();
                movie.setTitle(o.get("Title").getAsString());
                movie.setYear(o.get("Year").getAsString());
                movie.setId(o.get("imdbID").getAsString());
                movie.setType(o.get("Type").getAsString());
                movie.setPoster(o.get("Poster").getAsString());
                movie.setRuntime(o.get("Runtime").getAsString());
                results.add(movie);
            }
            return results;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

