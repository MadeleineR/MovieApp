package com.example.madeleine.movieapp;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends ListFragment implements DownloadJSONTask.AsyncListener {

    OnMovieSelectedListener mCallback;
    private List<Movie> movies;

    @Override
    public void setResults(ArrayList<Movie> results) {
        this.movies = results;
        MovieListAdapter adapter = new MovieListAdapter(this.getContext(), results.toArray(new Movie[results.size()]));
        setListAdapter(adapter);
    }


    public interface OnMovieSelectedListener {
        public void onMovieSelected(String movieId);
    }

    public MovieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText searchText = (EditText) getView().findViewById(R.id.inputSearch);
        searchText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    DownloadJSONTask downloadJSONTask = new DownloadJSONTask(MovieListFragment.this, 's');
                    String query = Uri.encode(v.getText().toString());
                    String uri = "http://www.omdbapi.com/?s=" + query;
                    downloadJSONTask.execute(uri);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnMovieSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMovieSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        String movieId = movies.get(position).getId();
        mCallback.onMovieSelected(movieId);
    }

}
