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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment implements DownloadJSONTask.AsyncListener {

    private String movieId;

    public MovieDetailFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        String movieId = getArguments().getString("movieId");
        DownloadJSONTask downloadJSONTask = new DownloadJSONTask(this, 'i');
        String query = Uri.encode(movieId);
        String uri = "http://www.omdbapi.com/?i=" + query;
        downloadJSONTask.execute(uri);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void setResults(ArrayList<Movie> results) {
        TextView textView = (TextView) getView().findViewById(R.id.title);
        textView.setText(results.get(0).getTitle());
        TextView yearTxt = (TextView) getView().findViewById(R.id.year);
        yearTxt.setText(results.get(0).getYear());
        TextView runtimeTxt = (TextView) getView().findViewById(R.id.runtime);
        runtimeTxt.setText(results.get(0).getRuntime());

        if(results.get(0).getPoster() != null && results.get(0).getPoster() != "") {
            WebView webView = (WebView) getView().findViewById(R.id.webView);
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = display.getWidth();

            String data = "<html><head><title>Example</title><meta name=\"viewport\"\"content=\"width=" + width + ", initial-scale=0.65 \" /></head>";
            data = data + "<body><center><img width=\"100%\" src=\"" + results.get(0).getPoster() + "\" /></center></body></html>";
            webView.loadData(data, "text/html", null);
        }


    }
}