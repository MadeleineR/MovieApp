package com.example.madeleine.movieapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;


@EFragment(R.layout.fragment_list)
public class MovieListFragment extends ListFragment {

    OnMovieSelectedListener mCallback;
    private List<Movie> movies;

    @ViewById
    EditText inputSearch;

    @RestService
    MovieDBClient movieDBClient;

    public MovieListFragment() {}


    public interface OnMovieSelectedListener {
        public void onMovieSelected(String movieId);
    }

    // important, otherwise view injection fails !!
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @EditorAction(R.id.inputSearch)
    void onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            String query = v.getText().toString();
            searchAsync(query);
        }
    }


    @Background
    void searchAsync(String searchString) {
        MovieList movieList = movieDBClient.getMovieList(searchString);
        System.out.println(movieList.getResponse());
        System.out.println(movieList.getError());
        if(movieList.getError() != null) {
            setResults(null);
        }
        else {
            setResults(movieList.getMovies());
        }
    }

    @UiThread
    public void setResults(List<Movie> results) {
        if(results != null) {
            this.movies = results;
            MovieListAdapter adapter = new MovieListAdapter(this.getContext(), results.toArray(new Movie[results.size()]));
            setListAdapter(adapter);
        }
        else {
            MovieListAdapter adapter = new MovieListAdapter(this.getContext(), new Movie[]{});
            setListAdapter(adapter);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnMovieSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnMovieSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String movieId = movies.get(position).getId();
        mCallback.onMovieSelected(movieId);
    }

}
