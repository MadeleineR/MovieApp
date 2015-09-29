package com.example.madeleine.movieapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements MovieListFragment.OnMovieSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        MovieListFragment listFragment = new MovieListFragment();
        fm.beginTransaction().add(R.id.fragment_content, listFragment).commit();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieSelected(String movieId) {
        FragmentManager fm = getSupportFragmentManager();

        MovieDetailFragment detailFragment = new MovieDetailFragment_();
        Bundle args = new Bundle();
        args.putString("movieId", movieId);
        detailFragment.setArguments(args);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, detailFragment);

        ft.addToBackStack(null);
        ft.commit();
    }

}
