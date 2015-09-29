package com.example.madeleine.movieapp;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_detail)
public class MovieDetailFragment extends Fragment implements DownloadJSONTask.AsyncListener {

    private String movieId;

    @ViewById
    TextView title;

    @ViewById
    TextView year;

    @ViewById
    TextView runtime;

    @ViewById
    WebView webView;

    public MovieDetailFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        String movieId = getArguments().getString("movieId");
        DownloadJSONTask downloadJSONTask = new DownloadJSONTask(this, 'i');
        String query = Uri.encode(movieId);
        String uri = "http://www.omdbapi.com/?i=" + query;
        downloadJSONTask.execute(uri);
    }



    @AfterViews
    void updateTextWithDate() {
        System.out.println("views bound!!!");
    }


    @Override
    public void setResults(ArrayList<Movie> results) {
        title.setText(results.get(0).getTitle());
        year.setText(results.get(0).getYear());
        runtime.setText(results.get(0).getRuntime());

        if(results.get(0).getPoster() != null && results.get(0).getPoster() != "") {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = display.getWidth();

            String data = "<html><head><title>Poster</title><meta name=\"viewport\"\"content=\"width=" + width + ", initial-scale=0.65 \" /></head>";
            data = data + "<body><center><img width=\"100%\" src=\"" + results.get(0).getPoster() + "\" /></center></body></html>";
            try {
                webView.loadData(URLEncoder.encode(data,"utf-8").replaceAll("\\+"," "), "text/html", "utf-8");

            } catch (UnsupportedEncodingException e) {
                webView.loadData("<html><head><title>Example</title><meta name=\"viewport\"\"content=\"width=" + width + ", initial-scale=0.65 \" /></head>", "text/html", "utf-8");
            }
            webView.loadData(data, "text/html", null);
        }
    }
}
