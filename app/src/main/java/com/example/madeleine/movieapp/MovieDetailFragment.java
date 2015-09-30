package com.example.madeleine.movieapp;


import android.support.v4.app.Fragment;
import android.view.Display;
import android.webkit.WebView;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_detail)
public class MovieDetailFragment extends Fragment {

    @FragmentArg
    String movieId;

    @ViewById
    TextView title;

    @ViewById
    TextView year;

    @ViewById
    TextView runtime;

    @ViewById
    WebView webView;

    @RestService
    MovieDBClient movieDBClient;


    public MovieDetailFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();

        String movieId = getArguments().getString("movieId");
        searchAsync(movieId);
    }

    @Background
    void searchAsync(String id) {
        Movie movie = movieDBClient.getMovie(id);
        setResults(movie);
    }

    @UiThread
    public void setResults(Movie result) {
        title.setText(result.getTitle());
        year.setText(result.getYear());
        runtime.setText(result.getRuntime());

        if(result.getPoster() != null && result.getPoster() != "") {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = display.getWidth();

            String data = "<html><head><title>Poster</title><meta name=\"viewport\"\"content=\"width=" + width + ", initial-scale=0.65 \" /></head>";
            data = data + "<body><center><img width=\"100%\" src=\"" + result.getPoster() + "\" /></center></body></html>";
            try {
                webView.loadData(URLEncoder.encode(data,"utf-8").replaceAll("\\+"," "), "text/html", "utf-8");

            } catch (UnsupportedEncodingException e) {
                webView.loadData("<html><head><title>Example</title><meta name=\"viewport\"\"content=\"width=" + width + ", initial-scale=0.65 \" /></head>", "text/html", "utf-8");
            }
            webView.loadData(data, "text/html", null);
        }
    }
}
