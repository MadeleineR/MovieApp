package com.example.madeleine.movieapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements MovieListFragment.OnMovieSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null) {
            MovieListFragment listFragment = MovieListFragment_.builder().build();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content, listFragment).commit();
        }
    }



    @Override
    public void onMovieSelected(String movieId) {
        FragmentManager fm = getSupportFragmentManager();

        MovieDetailFragment detailFragment = MovieDetailFragment_.builder().movieId(movieId).build();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, detailFragment);

        ft.addToBackStack(null);
        ft.commit();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, fragment);

        ft.addToBackStack(null);
        ft.commit();
    }

}
